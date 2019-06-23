package am.rate.core.model;

import java.util.List;
import java.util.Random;

public class Bank {

    private String guid;

    private String title;

    private long date;

    private String logo;

   private List<Currency> currency;

   private int position;


    public Bank(String guid) {
        this.guid = guid;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getLogo() {
        switch (position){
            case 0:
                return "https://res.cloudinary.com/hakobyangor/image/upload/c_scale,r_300," +
                        "w_300/v1559241639/game-of-thrones-daenerys-targaryen3-1558306532_o4cand.png";
            case 1:
                return "https://res.cloudinary.com/hakobyangor/image/upload/c_scale,r_300," +
                        "w_300/v1559240352/144110_hj4fgh.png";
            case 2:
                return "https://res.cloudinary.com/hakobyangor/image/upload/c_scale,r_300,w_300/v1559240352/166186_cy7dao.png";
            case 3:
                return "https://res.cloudinary.com/hakobyangor/image/upload/c_scale,r_300,w_300/v1559240352/184012_vrioxr.png";
            case 4:
                return "https://res.cloudinary.com/hakobyangor/image/upload/c_scale,r_300,w_300/v1559240352/149918_zbitmy.png";
            case 5:
                return "https://res.cloudinary.com/hakobyangor/image/upload/c_scale,r_300,w_300/v1559240355/140902_uezccy.png";
            case 6:
                return "https://res.cloudinary.com/hakobyangor/image/upload/c_scale,r_300,w_300/v1559240355/140087_yydesa.png";
            case 7:
                return "https://res.cloudinary.com/hakobyangor/image/upload/c_scale,r_300,w_300/v1559240353/117594_f0ujn2.png";
            case 8:
                return "https://res.cloudinary.com/hakobyangor/image/upload/c_scale,r_300,w_300/v1559240354/117577_txgezs.png";
            case 9:
                return "https://res.cloudinary.com/hakobyangor/image/upload/r_256/v1559240353/56249_ux63ct.png";
            case 10:
                return "https://res.cloudinary.com/hakobyangor/image/upload/c_scale,r_300,w_300/v1559240355/161279_efmnzo.png";
            default:
                return "https://res.cloudinary.com/hakobyangor/image/upload/c_scale,r_300,w_300/v1559282485/volkswagen-passat-b6-2005-3d-model-max-obj-3ds-fbx-c4d-lwo-lw-lws_qosgcc.png";
        }
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<Currency> getCurrency() {
        return currency;
    }

    public void setCurrency(List<Currency> currency) {
        this.currency = currency;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
