package pl.sda.poznan.model;

import javax.persistence.Embeddable;

@Embeddable
public class ZipCode {

    private String code;
    private String city;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
