import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== UJI Kendaraan =====");
        Kendaraan k1 = new Kendaraan("K01", "B 1234 XYZ", "Toyota", "Avanza", 2020, 350000);
        Kendaraan k2 = new Kendaraan("K02", "D 1111 AB", "Honda", "Brio", 2021, 300000);

        System.out.println("validasi K01: " + k1.validasiData());
        System.out.println("tersedia K01: " + k1.cekKetersediaan());
        k1.ubahStatusDisewa(true);
        System.out.println("tersedia setelah disewa: " + k1.cekKetersediaan());
        k1.tampilkanInfo();

        System.out.println("===== UJI Penyewa =====");
        Penyewa p1 = new Penyewa("P01", "Nabila Dwi Eka A", "2251505011110001",
                "081234567890", "Jl. Merdeka No. 1");
        Penyewa p2 = new Penyewa("P02", "", "123", "08123", "");

        System.out.println("validasi P01: " + p1.validasiIdentitas());
        System.out.println("validasi P02: " + p2.validasiIdentitas());
        p1.tampilkanInfo();

        System.out.println("===== UJI Sewa =====");
        Sewa s1 = new Sewa("S01", LocalDate.of(2026, 11, 24), 3, 350000, k1, p1);
        System.out.println("total biaya: " + s1.hitungTotalBiaya());
        System.out.println("tanggal kembali: " + s1.hitungTanggalKembali());
        s1.ubahStatus(false);
        s1.tampilkanInfo();

        System.out.println("===== UJI SortedListSewa =====");
        SortedListSewa list = new SortedListSewa(5);

        System.out.println("isEmpty awal: " + list.isEmpty());
        System.out.println("size awal: " + list.size());

        Sewa s2 = new Sewa("S02", LocalDate.of(2026, 11, 26), 2, 300000, k2, p1);
        Sewa s3 = new Sewa("S03", LocalDate.of(2026, 11, 24), 1, 350000, k1, p1);
        Sewa s4 = new Sewa("S04", LocalDate.of(2026, 11, 25), 4, 300000, k2, p1);

        list.insert(s2);
        list.insert(s3);
        list.insert(s4);

        System.out.println("isEmpty setelah insert: " + list.isEmpty());
        System.out.println("size setelah insert: " + list.size());
        System.out.println("isFull: " + list.isFull());

        System.out.println("-- tampilkanSemua --");
        list.tampilkanSemua();

        System.out.println("get(0): " + list.get(0).getClass().getSimpleName());
        System.out.println("findById S03: " + (list.findById("S03") != null));
        System.out.println("findById S99: " + (list.findById("S99") == null));

        System.out.println("remove S02: " + list.remove("S02"));
        System.out.println("remove S99: " + list.remove("S99"));
        System.out.println("size setelah remove: " + list.size());

        System.out.println("-- tampilkanSemua setelah remove --");
        list.tampilkanSemua();
    }
}
