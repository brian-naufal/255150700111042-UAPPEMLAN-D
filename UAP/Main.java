package UAP;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GoDriveRentalSystem sistem = new GoDriveRentalSystem();

        int opt = 0;

        while (true) {
            System.out.println("\n-- MENU GO DRIVE --");
            System.out.println("1. Tambah Kendaraan");
            System.out.println("2. Tampilkan Daftar Armada");
            System.out.println("3. Sewa Kendaraan");
            System.out.println("4. Kembalikan Kendaraan");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");

            try {
                opt = sc.nextInt();
                sc.nextLine();

                switch (opt) {
                    case 1:
                        System.out.print("Masukkan jenis kendaraan (mobil/motor): ");
                        String jenis = sc.nextLine().toLowerCase();

                        if (jenis.equals("mobil") || jenis.equals("motor")) {
                            System.out.print("Masukkan kode kendaraan: ");
                            String kode = sc.nextLine();
                            System.out.print("Masukkan nama kendaraan: ");
                            String nama = sc.nextLine();
                            System.out.print("Masukkan harga sewa perhari: ");
                            double sewa = sc.nextDouble();

                            if (jenis.equals("mobil")) {
                                System.out.print("Masukkan kapasitas kursi: ");
                                int kursi = sc.nextInt();
                                sc.nextLine();
                                sistem.tambahKendaraan(new Mobil(kode, nama, sewa, kursi));
                            } else {
                                sc.nextLine();
                                System.out.print("Masukkan jenis transmisi (matik/manual): ");
                                String transmisi = sc.nextLine().toLowerCase();
                                boolean isMatik = transmisi.equals("matik");
                                sistem.tambahKendaraan(new Motor(kode, nama, sewa, isMatik));
                            }
                        } else {
                            System.out.println("Jenis kendaraan tidak valid!");
                        }
                        break;

                    case 2:
                        sistem.tampilkanDaftar();
                        break;

                    case 3:
                        System.out.print("Masukkan kode kendaraan yang ingin disewa: ");
                        String kodeSewa = sc.nextLine();
                        System.out.print("Masukkan durasi sewa (hari): ");
                        int lama = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Apakah penyewa adalah member VIP? (y/n): ");
                        String vipInput = sc.nextLine().toLowerCase();
                        boolean isVip = vipInput.equals("y");

                        sistem.sewaKendaraan(kodeSewa, lama, isVip);
                        break;

                    case 4:
                        System.out.print("Masukkan kode kendaraan yang dikembalikan: ");
                        String kodeKembali = sc.nextLine();
                        sistem.kembalikanKendaraan(kodeKembali);
                        break;

                    case 5:
                        System.out.println("Terima kasih telah menggunakan sistem Go Drive!");
                        sc.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Pilihan menu tidak tersedia.");
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Input tidak valid. Pastikan memasukkan data berupa angka!");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }
}