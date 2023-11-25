package ru.dvteam.itcollabhub;

public class Model {
    private String ret;
    private String name;
    private int topScore;
    private String topStatus;
    private String urlImg;
    private String rFr;
    private String description;

    public Model(String name, String urlImg, String ret, String topStatus, int topScore, String rFr, String description) {
        this.name = name;
        this.ret = ret;
        this.urlImg = urlImg;
        this.topStatus = topStatus;
        this.topScore = topScore;
        this.rFr = rFr;
        this.description = description;
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
}
