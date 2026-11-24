public class Main {
    public static void main(String[] args) {
        Kendaraan valid = new Kendaraan("K01", "B 1234 XYZ", "Toyota", "Avanza", 2020, 350000);
        Kendaraan invalid = new Kendaraan("", "XX", "", "Avanza", 1800, 0);

        System.out.println("K01 valid? " + valid.validasiData());
        System.out.println("K02 valid? " + invalid.validasiData());

        System.out.println("K01 tersedia? " + valid.cekKetersediaan());
        System.out.println("K02 tersedia? " + invalid.cekKetersediaan());

        valid.ubahStatusDisewa(true);
        System.out.println("K01 setelah disewa, tersedia? " + valid.cekKetersediaan());

        valid.tampilkanInfo();
        invalid.tampilkanInfo();

        Penyewa penyewa = new Penyewa("P01", "Nabila Dwi Eka A", "2251505011110001", "081234567890", "Jl. Merdeka No. 1");
        Penyewa penyewaInvalid = new Penyewa("P02", "Budi", "123", "08123", "");

        System.out.println("P01 identitas valid? " + penyewa.validasiIdentitas());
        System.out.println("P02 identitas valid? " + penyewaInvalid.validasiIdentitas());

        penyewa.tampilkanInfo();
        System.out.println("Ubah kontak P01? " + penyewa.ubahDataKontak("089876543210", "Jl. Sudirman No. 2"));
        System.out.println("Ubah kontak P01 (invalid)? " + penyewa.ubahDataKontak("12", ""));
        penyewa.tampilkanInfo();
    }
}
