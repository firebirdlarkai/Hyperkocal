package in.firebird.hyperkocal;

import java.util.ArrayList;

public class City {

    private String name;
    private String thumbnail;
    private ArrayList service;

    public City(String name, String thumbnail, ArrayList service) {
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

    public ArrayList getService() {
        return service;
    }

    public void setService(ArrayList service) {
        this.service = service;
    }
}
