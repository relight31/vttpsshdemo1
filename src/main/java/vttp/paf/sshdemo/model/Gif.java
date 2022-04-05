package vttp.paf.sshdemo.model;

import jakarta.json.JsonObject;

public class Gif {
    private String source;
    private String url;
    private String title;

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static Gif createFromJsonObject(JsonObject object) {
        Gif gif = new Gif();
        gif.setTitle(object.getString("title"));
        gif.setUrl(object.getString("url"));
        gif.setSource(object.getJsonObject("images")
                .getJsonObject("fixed_width")
                .getString("url"));
        return gif;
    }
}
