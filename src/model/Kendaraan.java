package model;

public class Kendaraan {
    private final String platNomor;
    private final String merk;
    private final String jenis;
    private final long tarifPerHari;
    private boolean tersedia = true;

    public Kendaraan(String platNomor, String merk, String jenis, long tarifPerHari) {
        this.platNomor = platNomor;
        this.merk = merk;
        this.jenis = jenis;
        this.tarifPerHari = tarifPerHari;
    }

    public long hitungBiayaSewa(int jumlahHari) {
        long total = tarifPerHari * jumlahHari;

        if (jenis.equals("MOBIL")) {
            total += 5000;
        } else if (jenis.equals("MOTOR")) {
            if (jumlahHari >= 7) total -= 10;
        }

        return total;
    }

    public String getPlatNomor() {
        return platNomor;
    }

    public String getMerk() {
        return merk;
    }

    public String getJenis() {
        return jenis;
    }

    public long getTarifPerHari() {
        return tarifPerHari;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    public void setTersedia(boolean tersedia) {
        this.tersedia = tersedia;
    }
}
