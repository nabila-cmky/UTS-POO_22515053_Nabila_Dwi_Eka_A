import java.util.ArrayList;
import java.lang.reflect.Field;

public class Rental {
    private String idRental;
    private String namaRental;
    private String alamat;
    private String nomorTelepon;
    private ArrayList<Kendaraan> daftarKendaraan;
    private ArrayList<Penyewa> daftarPenyewa;
    private SortedListSewa daftarSewa;

    public Rental(String idRental, String namaRental, String alamat, String nomorTelepon) {
        this.idRental = idRental;
        this.namaRental = namaRental;
        this.alamat = alamat;
        this.nomorTelepon = nomorTelepon;
        this.daftarKendaraan = new ArrayList<>();
        this.daftarPenyewa = new ArrayList<>();
        this.daftarSewa = new SortedListSewa(100);
    }

    public void tambahKendaraan(Kendaraan k) {
        if (k == null || !k.validasiData()) {
            return;
        }
        try {
            Field f = Kendaraan.class.getDeclaredField("idKendaraan");
            f.setAccessible(true);
            String id = (String) f.get(k);
            for (Kendaraan existing : daftarKendaraan) {
                if (id != null && id.equals(f.get(existing))) {
                    return;
                }
            }
            daftarKendaraan.add(k);
        } catch (Exception e) {
            return;
        }
    }

    public void tambahPenyewa(Penyewa p) {
        if (p == null || !p.validasiIdentitas()) {
            return;
        }
        try {
            Field f = Penyewa.class.getDeclaredField("idPenyewa");
            f.setAccessible(true);
            String id = (String) f.get(p);
            for (Penyewa existing : daftarPenyewa) {
                if (id != null && id.equals(f.get(existing))) {
                    return;
                }
            }
            daftarPenyewa.add(p);
        } catch (Exception e) {
            return;
        }
    }

    public Kendaraan cariKendaraan(String id) {
        try {
            Field f = Kendaraan.class.getDeclaredField("idKendaraan");
            f.setAccessible(true);
            for (Kendaraan k : daftarKendaraan) {
                if (id.equals(f.get(k))) {
                    return k;
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public boolean buatSewa(Sewa s) {
        if (s == null) {
            return false;
        }
        try {
            Field fid = Sewa.class.getDeclaredField("idSewa");
            fid.setAccessible(true);
            String id = (String) fid.get(s);
            if (id == null || daftarSewa.findById(id) != null) {
                return false;
            }

            Field fl = Sewa.class.getDeclaredField("lamaHari");
            fl.setAccessible(true);
            if ((int) fl.get(s) <= 0) {
                return false;
            }

            Field fk = Sewa.class.getDeclaredField("kendaraan");
            fk.setAccessible(true);
            Kendaraan k = (Kendaraan) fk.get(s);
            if (k == null || !k.cekKetersediaan()) {
                return false;
            }

            s.hitungTotalBiaya();
            s.hitungTanggalKembali();
            if (!daftarSewa.insert(s)) {
                return false;
            }
            k.ubahStatusDisewa(true);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean akhiriSewa(String id) {
        Sewa s = daftarSewa.findById(id);
        if (s == null) {
            return false;
        }
        try {
            Field fa = Sewa.class.getDeclaredField("statusAktif");
            fa.setAccessible(true);
            if (!(boolean) fa.get(s)) {
                return false;
            }
            s.ubahStatus(false);
            Field fk = Sewa.class.getDeclaredField("kendaraan");
            fk.setAccessible(true);
            Kendaraan k = (Kendaraan) fk.get(s);
            if (k != null) {
                k.ubahStatusDisewa(false);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void tampilkanLaporan() {
        System.out.println("===== LAPORAN RENTAL =====");
        System.out.println("ID Rental   : " + idRental);
        System.out.println("Nama Rental : " + namaRental);
        System.out.println("Alamat      : " + alamat);
        System.out.println("Telepon     : " + nomorTelepon);
        System.out.println("Jumlah Kendaraan : " + daftarKendaraan.size());
        System.out.println("Jumlah Penyewa   : " + daftarPenyewa.size());
        System.out.println("Jumlah Transaksi : " + daftarSewa.size());
        System.out.println();
        System.out.println("----- DAFTAR TRANSAKSI -----");
        daftarSewa.tampilkanSemua();
    }
}
