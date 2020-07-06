package in.firebird.hyperkocal;

public class StatesHandler {
    private String id;
    private String name;
    private String thumbnail;

    public StatesHandler() {
    }

    public StatesHandler(String id, String name, String thumbnail) {
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
