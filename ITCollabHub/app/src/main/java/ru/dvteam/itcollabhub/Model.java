package ru.dvteam.itcollabhub;

public class Model {
    private String ret;
    private String name;
    private int topScore;
    private String topStatus;
    private String urlImg;

    public Model(String name, String urlImg, String ret, String topStatus, int topScore) {
        this.name = name;
        this.ret = ret;
        this.urlImg = urlImg;
        this.topStatus = topStatus;
        this.topScore = topScore;
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


}
