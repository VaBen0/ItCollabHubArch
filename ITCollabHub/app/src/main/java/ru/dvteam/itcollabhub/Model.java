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
    private double isend;
    private String purposes;
    private String problems;
    private String peoples;
    private String time;
    private String time1;
    private String tg;
    private String vk;
    private String webs;
    private String purposesids;
    private String problemsids;
    private String isl;

    public Model(String name, String urlImg, String ret, String topStatus, int topScore, String rFr, String description, String tgLink,
                 String vkLink, String webLink, double isend, String purposes, String problems, String peoples, String time, String time1,
                 String purposesids, String problemsids, String isl) {
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
        this.purposesids = purposesids;
        this.problemsids = problemsids;
        this.isl = isl;
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
    public String getTg() {return this.tg;}
    public double getIsend() {return this.isend;}
    public String getPeoples() {return this.peoples;}
    public String getProblems() {return this.problems;}
    public String getPurposes() {return this.purposes;}
    public String getRet() {return this.ret;}
    public String getTime() {return this.time;}
    public String getTime1() {return this.time1;}
    public String getVk() {return this.vk;}
    public String getWebs() {return this.webs;}
    public String getIsl() {return isl;}
    public String getProblemsids() {return problemsids;}
    public String getPurposesids() {return purposesids;}
}
