package entity;

public class DtPenjualan {

    private String id;

    private String idPenjualan;

    private String idBrg;

    private int jumlah;

    private int harga;

    public DtPenjualan(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPenjualan() {
        return idPenjualan;
    }

    public void setIdPenjualan(String idPenjualan) {
        this.idPenjualan = idPenjualan;
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
