public class Main {
    public static void main(String[] args) {
        Kendaraan kendaraan = new Kendaraan("K01", "B 1234 XYZ", "Toyota", "Avanza", 2020, 350000);

        System.out.println("===== UJI Kendaraan.validasiData() =====");
        System.out.println("Hasil validasi: " + kendaraan.validasiData());
        System.out.println();

        System.out.println("===== UJI Kendaraan.cekKetersediaan() =====");
        System.out.println("Tersedia: " + kendaraan.cekKetersediaan());
        System.out.println();

        System.out.println("===== UJI Kendaraan.ubahStatusDisewa(true) =====");
        kendaraan.ubahStatusDisewa(true);
        System.out.println("Tersedia setelah disewa: " + kendaraan.cekKetersediaan());
        System.out.println();

        System.out.println("===== UJI Kendaraan.tampilkanInfo() =====");
        kendaraan.tampilkanInfo();

        Penyewa penyewa = new Penyewa("P01", "Nabila Dwi Eka A", "2251505011110001",
                "081234567890", "Jl. Merdeka No. 1");
        Penyewa penyewaInvalid = new Penyewa("P02", "", "123", "08123", "");

        System.out.println("===== UJI Penyewa.validasiIdentitas() =====");
        System.out.println("Hasil validasi P01: " + penyewa.validasiIdentitas());
        System.out.println("Hasil validasi P02: " + penyewaInvalid.validasiIdentitas());
        System.out.println();

        System.out.println("===== UJI Penyewa.ubahDataKontak() =====");
        System.out.println("Kontak sebelum:");
        penyewa.tampilkanInfo();
        penyewa.ubahDataKontak("089876543210", "Jl. Sudirman No. 2");
        System.out.println("Kontak sesudah:");
        penyewa.tampilkanInfo();
        System.out.println("Ubah kontak dengan data tidak valid:");
        penyewa.ubahDataKontak("12", "");
        penyewa.tampilkanInfo();

        System.out.println("===== UJI Penyewa.tampilkanInfo() =====");
        penyewa.tampilkanInfo();

        Sewa sewa = new Sewa("S01", java.time.LocalDate.of(2026, 11, 24), 3, 350000,
                kendaraan, penyewa);

        System.out.println("===== UJI Sewa.hitungTotalBiaya() =====");
        System.out.println("Total biaya: " + sewa.hitungTotalBiaya());

        System.out.println("===== UJI Sewa.hitungTanggalKembali() =====");
        System.out.println("Tanggal mulai: " + java.time.LocalDate.of(2026, 11, 24));
        System.out.println("Tanggal kembali: " + sewa.hitungTanggalKembali());

        System.out.println("===== UJI Sewa.ubahStatus(false) =====");
        sewa.ubahStatus(false);
        System.out.println("Status diubah menjadi tidak aktif.");

        System.out.println("===== UJI Sewa.tampilkanInfo() =====");
        sewa.tampilkanInfo();
    }
}
