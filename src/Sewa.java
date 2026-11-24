import java.time.LocalDate;

public class Sewa {
    private String idSewa;
    private LocalDate tanggalMulai;
    private int lamaHari;
    private LocalDate tanggalKembali;
    private double tarifPerHari;
    private double totalBiaya;
    private boolean statusAktif;
    private Kendaraan kendaraan;
    private Penyewa penyewa;

    public Sewa(String idSewa, LocalDate tanggalMulai, int lamaHari,
                double tarifPerHari, Kendaraan kendaraan, Penyewa penyewa) {
        this.idSewa = idSewa;
        this.tanggalMulai = tanggalMulai;
        this.lamaHari = lamaHari;
        this.tarifPerHari = tarifPerHari;
        this.kendaraan = kendaraan;
        this.penyewa = penyewa;
        this.statusAktif = true;
    }

    public double hitungTotalBiaya() {
        totalBiaya = tarifPerHari * lamaHari;
        return totalBiaya;
    }

    public LocalDate hitungTanggalKembali() {
        tanggalKembali = tanggalMulai.plusDays(lamaHari);
        return tanggalKembali;
    }

    public void ubahStatus(boolean statusBaru) {
        statusAktif = statusBaru;
    }

    public void tampilkanInfo() {
        System.out.println("ID Sewa        : " + idSewa);
        System.out.println("Kendaraan      :");
        kendaraan.tampilkanInfo();
        System.out.println("Penyewa        :");
        penyewa.tampilkanInfo();
        System.out.println("Tanggal Mulai  : " + tanggalMulai);
        System.out.println("Lama Hari      : " + lamaHari);
        System.out.println("Tanggal Kembali: " + tanggalKembali);
        System.out.printf("Tarif per Hari : Rp%,.0f%n", tarifPerHari);
        System.out.printf("Total Biaya    : Rp%,.0f%n", totalBiaya);
        System.out.println("Status         : " + (statusAktif ? "Aktif" : "Selesai"));
        System.out.println();
    }
}
