package in.firebird.hyperkocal;

public class StatesHandler {
    private String name;
    private String thumbnail;

    public StatesHandler() {
    }

    public StatesHandler(String name, String thumbnail) {
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
}
