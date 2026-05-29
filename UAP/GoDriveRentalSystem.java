package UAP;

import java.util.ArrayList;
import java.util.List;

public class GoDriveRentalSystem {
    private List<Kendaraan> daftarKendaraan = new ArrayList<>();

    public void tambahKendaraan(Kendaraan k) {
        daftarKendaraan.add(k);
        System.out.println("Kendaraan " + k.getNamaKendaraan() + " berhasil ditambahkan.");
    }

    public void tampilkanDaftar() {
        System.out.println("\n-- DAFTAR ARMADA GODRIVE --");
        if (daftarKendaraan.isEmpty()) {
            System.out.println("Belum ada armada terdaftar.");
            return;
        }
        for (Kendaraan K : daftarKendaraan) {
            K.tampilInfo(); 
        }
    }

    public void sewaKendaraan(String kode, int lama, boolean isVip) throws Exception {
        Kendaraan target = null;
        for (Kendaraan K : daftarKendaraan) {
            if (K.getKodeKendaraan().equalsIgnoreCase(kode)) {
                target = K;
                break; 
            }
        }

        if (target == null) {
            throw new Exception("Kendaraan dengan kode '" + kode + "' tidak ditemukan di sistem.");
        }
        if (!target.getStatus()) {
            throw new Exception("Kendaraan '" + target.getNamaKendaraan() + "' sedang tidak tersedia (telah disewa).");
        }

        target.setStatus(false);
        double biayaDasar = target.hitungSewa(lama);
        
        double diskonPersen = 0;
        if (isVip) diskonPersen += 0.10;      
        if (lama > 7) diskonPersen += 0.05;   
        
        double potongan = biayaDasar * diskonPersen;
        double totalBayar = biayaDasar - potongan;

        System.out.println("\n--- NOTA TRANSAKSI PENYEWAAN ---");
        System.out.printf("%-18s: %s (%s)\n", "Unit", target.getNamaKendaraan(), target.getKodeKendaraan());
        System.out.printf("%-18s: %d hari\n", "Lama Sewa", lama);
        System.out.printf("%-18s: Rp %,.2f\n", "Biaya Dasar", biayaDasar);
        
        if (diskonPersen > 0) {
            System.out.printf("%-18s: Rp %,.2f (Diskon %d%%)\n", "Potongan Diskon", potongan, (int)(diskonPersen * 100));
        }
        System.out.printf("%-18s: Rp %,.2f\n", "TOTAL BAYAR", totalBayar);
        System.out.println("--------------------------------");
    }

    public void kembalikanKendaraan(String kode) throws Exception {
        Kendaraan target = null;
        for (Kendaraan k : daftarKendaraan){
            if (k.getKodeKendaraan().equalsIgnoreCase(kode)){
                target = k;
                break;
            }
        }
        
        if (target == null) {
            throw new Exception("Kendaraan dengan kode '" + kode + "' tidak ditemukan.");
        }
        if (target.getStatus()) {
            throw new Exception("Kendaraan '" + target.getNamaKendaraan() + "' statusnya saat ini memang tersedia.");
        }

        target.setStatus(true);
        System.out.println("Kendaraan " + target.getNamaKendaraan() + " berhasil dikembalikan. Status: Tersedia");
    }
}