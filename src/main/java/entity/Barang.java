package entity;

public class Barang {

    private String KdBrg;

    private String NmBrg;

    private String KdSat;

    private Integer Jml;

    public Barang() {
    }

    public String getKdBrg() {
        return this.KdBrg;
    }

    public void setKdBrg(String kdBrg) {
        this.KdBrg = kdBrg;
    }

    public String getNmBrg() {
        return this.NmBrg;
    }

    public void setNmBrg(String nmBrg) {
        this.NmBrg = nmBrg;
    }

    public String getKdSat() {
        return this.KdSat;
    }

    public void setKdSat(String kdSat) {
        this.KdSat = kdSat;
    }

    public Integer getJml() {
        return this.Jml;
    }

    public void setJml(Integer jml) {
        this.Jml = jml;
    }
}
