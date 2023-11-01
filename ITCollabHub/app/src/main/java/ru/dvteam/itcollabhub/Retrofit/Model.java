package ru.dvteam.itcollabhub;

public class Model {
    private String ret;
    private String name;

    public Model(String VHP, String ret) {
        this.name = VHP;
        this.ret = ret;
    }

    public String getName() {
        return name;
    }

    public String getReturn() {
        return ret;
    }
}
