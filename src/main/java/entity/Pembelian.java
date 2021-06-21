package entity;

import java.util.Date;

public class Pembelian {
    private String id;

    private String noPembelian;

    private Date tglPembelian;

    private String kdPemasok;

    public Pembelian(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoPembelian() {
        return noPembelian;
    }

    public void setNoPembelian(String noPembelian) {
        this.noPembelian = noPembelian;
    }

    public Date getTglPembelian() {
        return tglPembelian;
    }

    public void setTglPembelian(Date tglPembelian) {
        this.tglPembelian = tglPembelian;
    }

    public String getKdPemasok() {
        return kdPemasok;
    }

    public void setKdPemasok(String kdPemasok) {
        this.kdPemasok = kdPemasok;
    }
}
