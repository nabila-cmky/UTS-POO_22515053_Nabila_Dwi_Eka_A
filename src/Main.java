import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.reflect.Field;

public class Main {
    static Rental rental = new Rental("R01", "Rental Abadi", "Jl. Contoh 1", "081234567890");
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            tampilkanMenu();
            prosesPilihan();
        }
    }

    public static void tampilkanMenu() {
        System.out.println("\n===== MENU RENTAL =====");
        System.out.println("1. Tambah Kendaraan");
        System.out.println("2. Tambah Penyewa");
        System.out.println("3. Cari Kendaraan");
        System.out.println("4. Buat Sewa");
        System.out.println("5. Akhiri Sewa");
        System.out.println("6. Tampilkan Laporan");
        System.out.println("0. Keluar");
        System.out.print("Pilihan: ");
    }

    public static void prosesPilihan() {
        String pilih = sc.nextLine();
        switch (pilih) {
            case "1":
                System.out.print("ID Kendaraan: ");
                String id = sc.nextLine();
                System.out.print("No Polisi: ");
                String np = sc.nextLine();
                System.out.print("Merk: ");
                String merk = sc.nextLine();
                System.out.print("Tipe: ");
                String tipe = sc.nextLine();
                System.out.print("Tahun: ");
                int tahun = Integer.parseInt(sc.nextLine());
                System.out.print("Tarif per Hari: ");
                double tarif = Double.parseDouble(sc.nextLine());
                rental.tambahKendaraan(new Kendaraan(id, np, merk, tipe, tahun, tarif));
                System.out.println("Kendaraan diproses.");
                break;
            case "2":
                System.out.print("ID Penyewa: ");
                String idP = sc.nextLine();
                System.out.print("Nama: ");
                String nama = sc.nextLine();
                System.out.print("No Identitas (16 digit): ");
                String ni = sc.nextLine();
                System.out.print("No Telepon: ");
                String telp = sc.nextLine();
                System.out.print("Alamat: ");
                String alamat = sc.nextLine();
                rental.tambahPenyewa(new Penyewa(idP, nama, ni, telp, alamat));
                System.out.println("Penyewa diproses.");
                break;
            case "3":
                System.out.print("Masukkan ID Kendaraan: ");
                Kendaraan k = rental.cariKendaraan(sc.nextLine());
                if (k != null) {
                    k.tampilkanInfo();
                } else {
                    System.out.println("Kendaraan tidak ditemukan.");
                }
                break;
            case "4":
                System.out.print("ID Sewa: ");
                String idSewa = sc.nextLine();
                System.out.print("ID Kendaraan: ");
                String idKendaraan = sc.nextLine();
                System.out.print("ID Penyewa: ");
                String idPenyewa = sc.nextLine();
                System.out.print("Tanggal Mulai (YYYY-MM-DD): ");
                LocalDate tgl = LocalDate.parse(sc.nextLine());
                System.out.print("Lama Hari: ");
                int lama = Integer.parseInt(sc.nextLine());
                Kendaraan kd = rental.cariKendaraan(idKendaraan);
                Penyewa ps = null;
                try {
                    Field fd = Rental.class.getDeclaredField("daftarPenyewa");
                    fd.setAccessible(true);
                    ArrayList<?> daftar = (ArrayList<?>) fd.get(rental);
                    Field fi = Penyewa.class.getDeclaredField("idPenyewa");
                    fi.setAccessible(true);
                    for (Object item : daftar) {
                        if (item instanceof Penyewa && idPenyewa.equals(fi.get(item))) {
                            ps = (Penyewa) item;
                            break;
                        }
                    }
                } catch (Exception e) {
                    ps = null;
                }
                if (kd == null || ps == null) {
                    System.out.println("Kendaraan atau penyewa tidak ditemukan.");
                    break;
                }
                double tarifSewa = 0;
                try {
                    Field f = Kendaraan.class.getDeclaredField("tarifPerHari");
                    f.setAccessible(true);
                    tarifSewa = (double) f.get(kd);
                } catch (Exception e) {
                    break;
                }
                Sewa s = new Sewa(idSewa, tgl, lama, tarifSewa, kd, ps);
                System.out.println(rental.buatSewa(s) ? "Sewa berhasil dibuat." : "Sewa gagal dibuat.");
                break;
            case "5":
                System.out.print("ID Sewa yang diakhiri: ");
                System.out.println(rental.akhiriSewa(sc.nextLine()) ? "Sewa diakhiri." : "Gagal mengakhiri sewa.");
                break;
            case "6":
                rental.tampilkanLaporan();
                break;
            case "0":
                System.out.println("Keluar.");
                System.exit(0);
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }
}
