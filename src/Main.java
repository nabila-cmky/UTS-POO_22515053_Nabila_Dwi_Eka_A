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
        if (!sc.hasNextLine()) {
            System.exit(0);
        }
        String pilih = sc.nextLine().trim();
        switch (pilih) {
            case "1":
                System.out.print("ID Kendaraan: ");
                String id = sc.nextLine().trim();
                System.out.print("No Polisi: ");
                String np = sc.nextLine().trim();
                System.out.print("Merk: ");
                String merk = sc.nextLine().trim();
                System.out.print("Tipe: ");
                String tipe = sc.nextLine().trim();
                System.out.print("Tahun: ");
                String tahunStr = sc.nextLine().trim();
                System.out.print("Tarif per Hari: ");
                String tarifStr = sc.nextLine().trim();
                if (id.isEmpty() || np.isEmpty() || merk.isEmpty() || tipe.isEmpty()
                        || tahunStr.isEmpty() || tarifStr.isEmpty()) {
                    System.out.println("Semua field wajib diisi.");
                    break;
                }
                int tahun;
                double tarif;
                try {
                    tahun = Integer.parseInt(tahunStr);
                    tarif = Double.parseDouble(tarifStr);
                } catch (NumberFormatException e) {
                    System.out.println("Tahun harus angka, tarif harus angka.");
                    break;
                }
                Kendaraan k = new Kendaraan(id, np, merk, tipe, tahun, tarif);
                if (!k.validasiData()) {
                    System.out.println("Data kendaraan tidak valid.");
                    break;
                }
                if (rental.cariKendaraan(id) != null) {
                    System.out.println("ID kendaraan sudah terdaftar.");
                    break;
                }
                rental.tambahKendaraan(k);
                System.out.println("Kendaraan berhasil ditambahkan.");
                break;
            case "2":
                System.out.print("ID Penyewa: ");
                String idP = sc.nextLine().trim();
                System.out.print("Nama: ");
                String nama = sc.nextLine().trim();
                System.out.print("No Identitas (16 digit): ");
                String ni = sc.nextLine().trim();
                System.out.print("No Telepon: ");
                String telp = sc.nextLine().trim();
                System.out.print("Alamat: ");
                String alamat = sc.nextLine().trim();
                if (idP.isEmpty() || nama.isEmpty() || ni.isEmpty() || telp.isEmpty() || alamat.isEmpty()) {
                    System.out.println("Semua field wajib diisi.");
                    break;
                }
                Penyewa p = new Penyewa(idP, nama, ni, telp, alamat);
                if (!p.validasiIdentitas()) {
                    System.out.println("Identitas penyewa tidak valid.");
                    break;
                }
                boolean duplikatPenyewa = false;
                try {
                    Field fd = Rental.class.getDeclaredField("daftarPenyewa");
                    fd.setAccessible(true);
                    ArrayList<?> daftar = (ArrayList<?>) fd.get(rental);
                    Field fi = Penyewa.class.getDeclaredField("idPenyewa");
                    fi.setAccessible(true);
                    for (Object item : daftar) {
                        if (item instanceof Penyewa && idP.equals(fi.get(item))) {
                            duplikatPenyewa = true;
                            break;
                        }
                    }
                } catch (Exception e) {
                    duplikatPenyewa = false;
                }
                if (duplikatPenyewa) {
                    System.out.println("ID penyewa sudah terdaftar.");
                    break;
                }
                rental.tambahPenyewa(p);
                System.out.println("Penyewa berhasil ditambahkan.");
                break;
            case "3":
                System.out.print("Masukkan ID Kendaraan: ");
                String idCari = sc.nextLine().trim();
                if (idCari.isEmpty()) {
                    System.out.println("ID tidak boleh kosong.");
                    break;
                }
                Kendaraan hasil = rental.cariKendaraan(idCari);
                if (hasil != null) {
                    hasil.tampilkanInfo();
                } else {
                    System.out.println("Kendaraan tidak ditemukan.");
                }
                break;
            case "4":
                if (rental.isDaftarSewaPenuh()) {
                    System.out.println("Daftar sewa penuh (maksimal 100).");
                    break;
                }
                System.out.print("ID Sewa: ");
                String idSewa = sc.nextLine().trim();
                System.out.print("ID Kendaraan: ");
                String idKendaraan = sc.nextLine().trim();
                System.out.print("ID Penyewa: ");
                String idPenyewa = sc.nextLine().trim();
                System.out.print("Tanggal Mulai (YYYY-MM-DD): ");
                String tglStr = sc.nextLine().trim();
                System.out.print("Lama Hari: ");
                String lamaStr = sc.nextLine().trim();
                if (idSewa.isEmpty() || idKendaraan.isEmpty() || idPenyewa.isEmpty()
                        || tglStr.isEmpty() || lamaStr.isEmpty()) {
                    System.out.println("Semua field wajib diisi.");
                    break;
                }
                LocalDate tgl;
                int lama;
                try {
                    tgl = LocalDate.parse(tglStr);
                    lama = Integer.parseInt(lamaStr);
                } catch (Exception e) {
                    System.out.println("Tanggal harus YYYY-MM-DD, lama hari harus angka.");
                    break;
                }
                if (lama <= 0) {
                    System.out.println("Lama hari harus lebih dari nol.");
                    break;
                }
                Kendaraan kd = rental.cariKendaraan(idKendaraan);
                if (kd == null) {
                    System.out.println("Kendaraan tidak ditemukan.");
                    break;
                }
                if (!kd.cekKetersediaan()) {
                    System.out.println("Kendaraan sedang disewa.");
                    break;
                }
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
                if (ps == null) {
                    System.out.println("Penyewa tidak ditemukan.");
                    break;
                }
                double tarifSewa = 0;
                try {
                    Field f = Kendaraan.class.getDeclaredField("tarifPerHari");
                    f.setAccessible(true);
                    tarifSewa = (double) f.get(kd);
                } catch (Exception e) {
                    System.out.println("Tarif kendaraan tidak dapat diambil.");
                    break;
                }
                Sewa s = new Sewa(idSewa, tgl, lama, tarifSewa, kd, ps);
                System.out.println(rental.buatSewa(s) ? "Sewa berhasil dibuat." : "Sewa gagal dibuat.");
                break;
            case "5":
                System.out.print("ID Sewa yang diakhiri: ");
                String idAkhir = sc.nextLine().trim();
                if (idAkhir.isEmpty()) {
                    System.out.println("ID sewa tidak boleh kosong.");
                    break;
                }
                System.out.println(rental.akhiriSewa(idAkhir) ? "Sewa diakhiri." : "Gagal mengakhiri sewa.");
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
