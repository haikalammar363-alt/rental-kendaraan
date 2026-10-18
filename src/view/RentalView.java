package view;

import java.util.List;
import java.util.Scanner;
import model.Kendaraan;
import model.Penyewaan;

public class RentalView {
    private final Scanner scanner = new Scanner(System.in);

    public String tampilMenu(){
        System.out.println("=== MENU RENTAL KENDARAAN ===");
        System.out.println("1. Daftar Kendaraan");
        System.out.println("2. Sewa Kendaraan");
        System.out.println("3. Riwayat Penyewaan");
        System.out.println("4. Kembalikan Kendaraan");
        System.out.println("5. Riwayat Pesanan");
        System.out.println("0. Keluar");
        return scanner.nextLine();
    }

    public void tampilDaftarKendaraan(List<Kendaraan> daftarKendaraan){
        System.out.println("=== DAFTAR KENDARAAN ===");
        if (daftarKendaraan.isEmpty()) {
            System.out.println("Tidak ada kendaraan yang tersedia.");
            return;
        }
        for (Kendaraan k : daftarKendaraan) {
            System.out.println("Merk: " + k.getMerk() + ", Plat Nomor: " + k.getPlatNomor() + ", Jenis: " + k.getJenis() + ", Harga Sewa: " + Format.formatRupiah(k.getTarifPerHari()) + ", Status: " + (k.isTersedia() ? "Tersedia" : "Tidak Tersedia"));
        }
    }

    public void riwayatPenyewaan(List<Penyewaan> riwayat){
        System.out.println("=== RIWAYAT PENYEWAAN ===");
        if (riwayat.isEmpty()) {
            System.out.println("Tidak ada riwayat penyewaan.");
            return;
        }
        for (Penyewaan p : riwayat) {
            System.out.println("ID Penyewaan: " + p.getId() + ", Kendaraan: " + p.getKendaraan().getMerk() + " (" + p.getKendaraan().getPlatNomor() + "), Nama Penyewa: " + p.getNamaPenyewa() + ", Durasi Sewa: " + p.getLamaHari() + " hari, Total Biaya: " + Format.formatRupiah(p.getTotalBiaya()) + ", Status: " + (p.isAktif() ? "Aktif" : "Selesai"));
        }
    }

    public String bacaTeks(String label){
        System.out.println("Label: " + label);
        return scanner.nextLine().trim();
    }

    public long bacaAngka(String label){
        while (true) {
            String teks = bacaTeks(label);
            try {
                return Long.parseLong(teks);
            } catch (NumberFormatException e) {
                System.out.println("Harap masukkan angka.");
            }
        }
    }

    public void tampilPesan(String pesan){
        System.out.println(pesan);
    }
}
