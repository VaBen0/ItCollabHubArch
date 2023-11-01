package ru.dvteam.itcollabhub;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("Request")
    public String Req;
    @SerializedName("KirillHP")
    public int KHP;
    @SerializedName("VanyaHP")
    public int VHP;
    @SerializedName("BoostKirill")
    public int BoostK;
    @SerializedName("BoostVanya")
    public int BoostV;
    public User(String req, int khp, int vhp, int kbo, int vbo){
        this.Req = req;
        this.VHP = vhp;
        this.KHP = khp;
        this.BoostK = kbo;
        this.BoostV = vbo;
    }
}