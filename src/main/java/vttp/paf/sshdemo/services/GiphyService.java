package vttp.paf.sshdemo.services;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import vttp.paf.sshdemo.model.Gif;

@Service
public class GiphyService {
    final static String GIPHY_PUBLIC_API = "https://api.giphy.com/v1/gifs/search";

    @Value("${giphy.api.key}")
    private String giphyKey;

    public List<Gif> searchGifs(String phrase) {
        return searchGifs(phrase, 10, "pg");
    }

    public List<Gif> searchGifs(String phrase, int limit) {
        return searchGifs(phrase, limit, "pg");
    }

    public List<Gif> searchGifs(String phrase, String rating) {
        return searchGifs(phrase, 10, rating);
    }

    public List<Gif> searchGifs(String phrase, int limit, String rating) {
        List<Gif> results = new LinkedList<>();
        String searchGifsString = UriComponentsBuilder.fromUriString(GIPHY_PUBLIC_API)
                .queryParam("api_key", giphyKey)
                .queryParam("q", phrase)
                .queryParam("limit", String.valueOf(limit))
                .queryParam("rating", rating)
                .toUriString();
        RequestEntity<Void> req = RequestEntity.get(searchGifsString)
                .accept(MediaType.APPLICATION_JSON)
                .build();
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);
        JsonReader reader = Json.createReader(new StringReader(resp.getBody()));
        JsonObject object = reader.readObject();
        JsonArray data = object.getJsonArray("data");
        for (JsonValue item : data) {
            Gif gif = Gif.createFromJsonObject(item.asJsonObject());
            results.add(gif);
        }
        return results;
    }
}
