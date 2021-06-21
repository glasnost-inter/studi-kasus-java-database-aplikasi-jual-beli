package entity;

public class DtPembelian {
    private String id;

    private String idPembelian;

    private String idBrg;

    private int jumlah;

    private int harga;

    public DtPembelian(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPembelian() {
        return idPembelian;
    }

    public void setIdPembelian(String idPembelian) {
        this.idPembelian = idPembelian;
    }

    public String getIdBrg() {
        return idBrg;
    }

    public void setIdBrg(String idBrg) {
        this.idBrg = idBrg;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}
