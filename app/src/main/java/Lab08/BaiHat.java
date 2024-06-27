package Lab08;

import java.io.Serializable;

public class BaiHat implements Serializable {
    private String tenBai;
    private String tacGia;
    private String link;

    public BaiHat(String tenBai, String tacGia, String link) {
        this.tenBai = tenBai;
        this.tacGia = tacGia;
        this.link = link;
    }

    public String getTenBaiHat() {
        return tenBai;
    }

    public String getTacGia() {
        return tacGia;
    }

    public String getLink() {
        return link;
    }
}

