package view;

import model.Penyewaan;

public class StrukView {
    public void cetak(Penyewaan p){
        System.out.println("=== STRUK PENYEWAAN ===");
        System.out.println("ID Penyewaan: " + p.getId());
        System.out.println("Kendaraan: " + p.getKendaraan().getMerk() + " (" + p.getKendaraan().getPlatNomor() + ")");
        System.out.println("Nama Penyewa: " + p.getNamaPenyewa());
        System.out.println("Jenis: " + p.getKendaraan().getJenis());
        System.out.println("Durasi Sewa: " + p.getLamaHari() + " hari");
        System.out.println("Total Biaya: " + Format.formatRupiah(p.getTotalBiaya()));
        System.out.println("========================");
    }
}
