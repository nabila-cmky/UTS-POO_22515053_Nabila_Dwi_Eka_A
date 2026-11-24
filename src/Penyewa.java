public class Penyewa {
    private String idPenyewa;
    private String nama;
    private String nomorIdentitas;
    private String nomorTelepon;
    private String alamat;

    public Penyewa(String idPenyewa, String nama, String nomorIdentitas,
                   String nomorTelepon, String alamat) {
        this.idPenyewa = idPenyewa;
        this.nama = nama;
        this.nomorIdentitas = nomorIdentitas;
        this.nomorTelepon = nomorTelepon;
        this.alamat = alamat;
    }

    public boolean validasiIdentitas() {
        return idPenyewa != null && !idPenyewa.isBlank()
            && nama != null && !nama.isBlank()
            && nomorIdentitas != null && nomorIdentitas.matches("\\d{16}");
    }

    public boolean ubahDataKontak(String teleponBaru, String alamatBaru) {
        if (teleponBaru == null || !teleponBaru.matches("\\d{10,13}")
                || alamatBaru == null || alamatBaru.isBlank()) {
            return false;
        }
        nomorTelepon = teleponBaru;
        alamat = alamatBaru;
        return true;
    }

    public void tampilkanInfo() {
        System.out.println("ID           : " + idPenyewa);
        System.out.println("Nama         : " + nama);
        System.out.println("No Identitas : " + nomorIdentitas);
        System.out.println("No Telepon   : " + nomorTelepon);
        System.out.println("Alamat       : " + alamat);
        System.out.println();
    }
}
