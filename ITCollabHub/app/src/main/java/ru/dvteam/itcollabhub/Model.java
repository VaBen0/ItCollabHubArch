package ru.dvteam.itcollabhub;

public class Model {
    private String ret;
    private String name;
    private String tgLink;
    private String vkLink;
    private String webLink;
    private int topScore;
    private String topStatus;
    private String urlImg;
    private String rFr;
    private String description;

    public Model(String name, String urlImg, String ret, String topStatus, int topScore, String rFr, String description, String tgLink, String vkLink, String webLink) {
        this.name = name;
        this.ret = ret;
        this.urlImg = urlImg;
        this.topStatus = topStatus;
        this.topScore = topScore;
        this.rFr = rFr;
        this.description = description;
        this.tgLink = tgLink;
        this.vkLink = vkLink;
        this.webLink = webLink;
    }

    public String getName() {
        return this.name;
    }

    public String getReturn() {
        return this.ret;
    }

    public int getTopScore(){return this.topScore;}

    public String getTopStatus(){return this.topStatus;}

    public String getUrlImg(){return this.urlImg;}

    public String getrFr(){return this.rFr;}

    public String getDescription() {return this.description;}

    public String getTgLink(){return this.tgLink;}
    public String getVkLink(){return this.vkLink;}
    public String getWebLink(){return this.webLink;}
}
