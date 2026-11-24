public class Main {
    public static void main(String[] args) {
        Kendaraan valid = new Kendaraan("K01", "B 1234 XYZ", "Toyota", "Avanza", 2020, 350000);
        Kendaraan invalid = new Kendaraan("", "XX", "", "Avanza", 1800, 0);

        System.out.println("K01 valid? " + valid.validasiData());
        System.out.println("K02 valid? " + invalid.validasiData());

        System.out.println("K01 tersedia? " + valid.cekKetersediaan());
        System.out.println("K02 tersedia? " + invalid.cekKetersediaan());
    }
}
