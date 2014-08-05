package model.dao;

/**
 * Created by then on 2014/8/5.
 */
public class GPDAO {
    String code;
    String name;
    String heigh;
    String low;
    String open;
    String close;
    String zt;
    String dt;
    boolean openZT;
    boolean hasZT;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeigh() {
        return heigh;
    }

    public void setHeigh(String heigh) {
        this.heigh = heigh;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public boolean isOpenZT() {
        return openZT;
    }

    public void setOpenZT(boolean openZT) {
        this.openZT = openZT;
    }

    public boolean isHasZT() {
        return hasZT;
    }

    public void setHasZT(boolean hasZT) {
        this.hasZT = hasZT;
    }
}
