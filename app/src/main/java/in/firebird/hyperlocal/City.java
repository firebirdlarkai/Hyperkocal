package in.firebird.hyperlocal;

import java.util.HashMap;

public class City {

    private String name;
    private String thumbnail;
    private HashMap service;

    public City(String name, String thumbnail, HashMap service) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.service = service;
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

    public HashMap getService() {
        return service;
    }

    public void setService(HashMap service) {
        this.service = service;
    }
}
