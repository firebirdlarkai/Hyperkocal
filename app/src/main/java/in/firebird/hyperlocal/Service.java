package in.firebird.hyperlocal;

public class Service {
    private String name;
    private String description;
    private String playstoreLink;
    private String appstoreLink;
    private String webLink;
    private String type;
    private String logo;
    private String autoFetch;


    public Service(String name, String description, String playstoreLink, String appstoreLink, String webLink, String type, String logo, String autoFetch) {
        this.name = name;
        this.description = description;
        this.playstoreLink = playstoreLink;
        this.appstoreLink = appstoreLink;
        this.webLink = webLink;
        this.type = type;
        this.logo = logo;
        this.autoFetch = autoFetch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlaystoreLink() {
        return playstoreLink;
    }

    public void setPlaystoreLink(String playstoreLink) {
        this.playstoreLink = playstoreLink;
    }

    public String getAppstoreLink() {
        return appstoreLink;
    }

    public void setAppstoreLink(String appstoreLink) {
        this.appstoreLink = appstoreLink;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public String getAutoFetch() {
        return autoFetch;
    }

    public void setAutoFetch(String autoFetch) {
        this.autoFetch = autoFetch;
    }
}
