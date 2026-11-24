public class Kendaraan {
    private String idKendaraan;
    private String nomorPolisi;
    private String merk;
    private String tipe;
    private int tahun;
    private double tarifPerHari;
    private boolean statusDisewa;

    public Kendaraan(String idKendaraan, String nomorPolisi, String merk,
                     String tipe, int tahun, double tarifPerHari) {
        this.idKendaraan = idKendaraan;
        this.nomorPolisi = nomorPolisi;
        this.merk = merk;
        this.tipe = tipe;
        this.tahun = tahun;
        this.tarifPerHari = tarifPerHari;
    }

    public boolean validasiData() {
        return idKendaraan != null && !idKendaraan.isBlank()
            && nomorPolisi != null && nomorPolisi.matches("[A-Z]{0,2}\\s?\\d{1,4}\\s?[A-Z]{1,3}")
            && merk != null && !merk.isBlank()
            && tipe != null && !tipe.isBlank()
            && tahun >= 1900 && tahun <= java.time.Year.now().getValue()
            && tarifPerHari > 0;
    }

    public boolean cekKetersediaan() {
        return !statusDisewa;
    }

    public void ubahStatusDisewa(boolean status) {
        this.statusDisewa = status;
    }

    public void tampilkanInfo() {
        System.out.println("ID        : " + idKendaraan);
        System.out.println("No Polisi : " + nomorPolisi);
        System.out.println("Merk      : " + merk);
        System.out.println("Tipe      : " + tipe);
        System.out.println("Tahun     : " + tahun);
        System.out.printf("Tarif     : Rp%,.0f/hari%n", tarifPerHari);
        System.out.println("Status    : " + (statusDisewa ? "Disewa" : "Tersedia"));
        System.out.println();
    }
}
