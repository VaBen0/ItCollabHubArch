package ru.dvteam.itcollabhub;

public class Model {
    private String ret;
    private String name;
    private String topScore;
    private String topStatus;

    public Model(String name, String ret, String topStatus, String topScore) {
        this.name = name;
        this.ret = ret;
        this.topStatus = topStatus;
        this.topScore = topScore;
    }

    public String getName() {
        return name;
    }

    public String getReturn() {
        return ret;
    }

    public String getTopScore(){return topScore;}

    public String getTopStatus(){return topStatus;}
}
