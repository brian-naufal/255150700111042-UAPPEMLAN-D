package UAP;

public abstract class Kendaraan {
    private String kode;
    private String nama;
    private double sewa;
    private boolean status = true;

    public Kendaraan(String kode, String nama, double sewa) {
        this.kode = kode;
        this.nama = nama;
        this.sewa = sewa;
    }

    public String getKodeKendaraan() {
        return kode;
    }

    public String getNamaKendaraan() {
        return nama;
    }

    public double getSewa() {
        return sewa;
    }

    public boolean getStatus() {
        return status;
    }

    public void setKodeKendaraan(String kode) {
        this.kode = kode;
    }

    public void setNamaKendaraan(String nama) {
        this.nama = nama;
    }

    public void setSewa(double sewa) {
        this.sewa = sewa;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public abstract double hitungSewa(int lamaSewa);

    public abstract void tampilInfo();
}

class Mobil extends Kendaraan {
    private int jumlahKursi;

    public Mobil(String kode, String nama, double sewa, int jumlahKursi) {
        super(kode, nama, sewa);
        this.jumlahKursi = jumlahKursi;
    }

    public int getKursi() {
        return jumlahKursi;
    }

    public void setKursi(int kursi) {
        this.jumlahKursi = kursi;
    }

    @Override
    public double hitungSewa(int lamaSewa) {
        double totalBiaya = lamaSewa * getSewa();
        if (jumlahKursi > 5) {
            totalBiaya += 50000;
        }
        return totalBiaya;
    }

    @Override
    public void tampilInfo() {
        System.out.printf("[MOBIL] Kode: %s | Nama: %s | Kursi: %d | Tarif: Rp%,.2f/hari | Status: %s%n",
                getKodeKendaraan(), getNamaKendaraan(), getKursi(), getSewa(),
                getStatus() ? "Tersedia" : "Tidak Tersedia");
    }
}

class Motor extends Kendaraan {
    private boolean isMatik;

    public Motor(String kode, String nama, double sewa, boolean isMatik) {
        super(kode, nama, sewa);
        this.isMatik = isMatik;
    }

    public boolean getTransmisi() {
        return isMatik;
    }

    public void setTransmisi(boolean isMatik) {
        this.isMatik = isMatik;
    }

    @Override
    public double hitungSewa(int lamaSewa) {
        double totalBiaya = lamaSewa * getSewa();
        if (isMatik) {
            totalBiaya += (10000 * lamaSewa);
        }
        return totalBiaya;
    }

    @Override
    public void tampilInfo() {
        System.out.printf("[MOTOR] Kode: %s | Nama: %s | Transmisi: %s | Tarif: Rp%,.2f/hari | Status: %s%n",
                getKodeKendaraan(), getNamaKendaraan(), isMatik ? "Matik" : "Manual", getSewa(),
                getStatus() ? "Tersedia" : "Tidak Tersedia");
    }
}