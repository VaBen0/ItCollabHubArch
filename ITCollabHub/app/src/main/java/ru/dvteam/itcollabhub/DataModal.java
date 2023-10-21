package ru.dvteam.itcollabhub;

public class DataModal {
    // string variables for our name and job
    private String Command;

    public DataModal(String request) {
        this.Command = request;
    }

    public String getRequest() {
        return Command;
    }

    public void setRequest(String request) {
        this.Command = request;
    }
}
