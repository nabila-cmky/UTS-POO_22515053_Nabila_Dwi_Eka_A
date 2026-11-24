import java.lang.reflect.Field;
import java.time.LocalDate;

public class SortedListSewa {
    private Sewa[] data;
    private int jumlah;
    private int kapasitas;

    public SortedListSewa(int kapasitas) {
        if (kapasitas <= 0) {
            throw new IllegalArgumentException("Kapasitas harus lebih dari nol.");
        }
        this.kapasitas = kapasitas;
        this.data = new Sewa[kapasitas];
        this.jumlah = 0;
    }

    public boolean isEmpty() {
        return jumlah == 0;
    }

    public boolean isFull() {
        return jumlah == kapasitas;
    }

    private LocalDate ambilTanggalMulai(Sewa sewa) {
        try {
            Field f = Sewa.class.getDeclaredField("tanggalMulai");
            f.setAccessible(true);
            return (LocalDate) f.get(sewa);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean insert(Sewa sewa) {
        if (sewa == null) {
            return false;
        }
        if (isFull()) {
            return false;
        }

        LocalDate tglBaru = ambilTanggalMulai(sewa);
        int pos = jumlah;
        while (pos > 0) {
            LocalDate tglLama = ambilTanggalMulai(data[pos - 1]);
            if (tglLama != null && tglBaru != null && tglLama.compareTo(tglBaru) > 0) {
                data[pos] = data[pos - 1];
                pos--;
            } else {
                break;
            }
        }
        data[pos] = sewa;
        jumlah++;
        return true;
    }

    public Sewa get(int index) {
        if (index < 0 || index >= jumlah) {
            return null;
        }
        return data[index];
    }

    private String ambilIdSewa(Sewa sewa) {
        try {
            Field f = Sewa.class.getDeclaredField("idSewa");
            f.setAccessible(true);
            return (String) f.get(sewa);
        } catch (Exception e) {
            return null;
        }
    }

    public Sewa findById(String idSewa) {
        for (int i = 0; i < jumlah; i++) {
            String id = ambilIdSewa(data[i]);
            if (id != null && id.equals(idSewa)) {
                return data[i];
            }
        }
        return null;
    }

    public boolean remove(String idSewa) {
        int pos = -1;
        for (int i = 0; i < jumlah; i++) {
            String id = ambilIdSewa(data[i]);
            if (id != null && id.equals(idSewa)) {
                pos = i;
                break;
            }
        }
        if (pos == -1) {
            return false;
        }
        for (int i = pos; i < jumlah - 1; i++) {
            data[i] = data[i + 1];
        }
        data[jumlah - 1] = null;
        jumlah--;
        return true;
    }

    public int size() {
        return jumlah;
    }

    public void tampilkanSemua() {
        if (isEmpty()) {
            System.out.println("Belum ada transaksi.");
            return;
        }
        for (int i = 0; i < jumlah; i++) {
            data[i].tampilkanInfo();
        }
    }
}
