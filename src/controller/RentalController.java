package controller;

import java.util.HashMap;
import java.util.Map;
import model.Kendaraan;
import model.KendaraanRepository;
import model.Penyewaan;
import model.PenyewaanRepository;
import view.RentalView;
import view.StrukView;

public class RentalController {
    private final KendaraanRepository kendaraanRepository = new KendaraanRepository();
    private final PenyewaanRepository penyewaanRepository = new PenyewaanRepository();
    private final StrukView strukView = new StrukView();
    private final RentalView rentalView = new view.RentalView();
    private final Map<String, Penyewaan> penyewaanAktif = new HashMap<>();

    public void tambahData(Kendaraan kendaraan) {
        kendaraanRepository.tambah(kendaraan);
    }

    public void jalankan(){
        while(true){
            String pilihan = rentalView.bacaTeks("Menu: 1. Tampilkan Kendaraan 2. Tambah Kendaraan, 3. Sewa Kendaraan, 4. Kembalikan Kendaraan, 5. Keluar");
            switch (pilihan) {
                case "1" -> rentalView.tampilDaftarKendaraan(kendaraanRepository.semua());
                case "2" -> tambahKendaraan();
                case "3" -> sewaKendaraan();
                case "4" -> kembalikanKendaraan();
                case "5" -> {
                    rentalView.tampilPesan("Terima kasih telah menggunakan layanan kami.");
                    return;
                }
                default -> rentalView.tampilPesan("Pilihan tidak valid.");
            }
        }
    }

    public void tambahKendaraan(){
        String platNomor = rentalView.bacaTeks("Plat Nomor: ");
        if (platNomor.isEmpty() || kendaraanRepository.cariByPlatNomor(platNomor) != null) {
            rentalView.tampilPesan("Plat nomor tidak valid atau sudah ada.");
            return;
        }
        String merk = rentalView.bacaTeks("Merk: ");
        String jenis = rentalView.bacaTeks("Jenis (MOBIL/MOTOR): ").toUpperCase();
        if (!jenis.equals("MOTOR") && !jenis.equals("MOBIL")){
            rentalView.tampilPesan("Jenis tidak dikenali");
            return;
        }

        long tarifPerHari = rentalView.bacaAngka("Tarif per Hari: ");
        if (tarifPerHari <= 0) {
            rentalView.tampilPesan("Tarif per hari harus lebih dari 0.");
            return;
        }

        kendaraanRepository.tambah(new Kendaraan(platNomor, merk, jenis, tarifPerHari));
        rentalView.tampilPesan("Kendaraan berhasil ditambahkan.");
        
    }

    public void sewaKendaraan(){
        String plat = rentalView.bacaTeks("Plat Nomor Kendaraan: ");
        Kendaraan kendaraan = kendaraanRepository.cariByPlatNomor(plat);
        if (kendaraan == null || !kendaraan.isTersedia()) {
            rentalView.tampilPesan("Kendaraan tidak ditemukan atau tidak tersedia.");
            return;
        }

        String namaPenyewa = rentalView.bacaTeks("Nama Penyewa: ");
        int lamaHari = (int) rentalView.bacaAngka("Lama Sewa (hari): ");
        if (lamaHari <= 0) {
            rentalView.tampilPesan("Lama sewa harus lebih dari 0 hari.");
            return;
        }

        long totalBiaya = kendaraan.hitungBiayaSewa(lamaHari);
        Penyewaan sewa = new Penyewaan(plat, namaPenyewa, kendaraan, lamaHari, totalBiaya);
        penyewaanRepository.tambah(sewa);
        penyewaanAktif.put(plat, sewa);
        kendaraan.setTersedia(false);
        rentalView.tampilPesan("Kendaraan berhasil disewa. Total Biaya: " + totalBiaya);
        strukView.cetak(sewa);
    }

    public void kembalikanKendaraan(){
        String plat = rentalView.bacaTeks("Plat Nomor Kendaraan: ");
        Penyewaan penyewaan = penyewaanAktif.get(plat);
        if (penyewaan == null) {
            rentalView.tampilPesan("Tidak ada penyewaan aktif untuk kendaraan ini.");
            return;
        }

        penyewaan.selesai();
        penyewaanAktif.remove(plat);
        penyewaan.getKendaraan().setTersedia(true);
        rentalView.tampilPesan("Kendaraan berhasil dikembalikan.");
    }


}
