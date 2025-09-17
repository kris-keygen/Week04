package week04.kristian.id.ac.umn;

import java.util.ArrayList;
import java.util.Scanner;

public class Assignment {
    static class Barang {
        private int id, stock, harga;
        private String nama;

        public Barang(int id, String nama, int stock, int harga) {
            this.id = id;
            this.nama = nama;
            this.stock = stock;
            this.harga = harga;
        }

        public int getId() { return id; }
        public int getStock() { return stock; }
        public int getHarga() { return harga; }
        public String getNama() { return nama; }

        public void minusStock(int qty) {
            this.stock -= qty;
        }
    }

    static class Order {
        private int id, jumlah;
        private Barang barang;
        public static int total = 0;

        public Order(int id, Barang barang, int jumlah) {
            this.id = id;
            this.barang = barang;
            this.jumlah = jumlah;
            total += jumlah * barang.getHarga();
        }

        public int getId() { return id; }
        public int getJumlah() { return jumlah; }
        public Barang getBarang() { return barang; }
    }

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Barang> daftarBarang = new ArrayList<>();
    static ArrayList<Order> daftarPesanan = new ArrayList<>();

    public static void main(String[] args) {
        seedData();
        while (true) {
            menuUtama();
            int pilih = sc.nextInt();
            switch (pilih) {
                case 1 -> pesanBarang();
                case 2 -> lihatPesanan();
                default -> {
                    System.out.println("Program selesai.");
                    return;
                }
            }
        }
    }

    public static void seedData() {
        daftarBarang.add(new Barang(1, "Pulpen Easy Gel 0.5mm", 120, 2000));
        daftarBarang.add(new Barang(2, "Penggaris 30cm", 30, 5000));
        daftarBarang.add(new Barang(3, "Tipe-x Roller", 30, 7000));
        daftarBarang.add(new Barang(4, "Pensil Mekanik", 50, 5000));
        daftarBarang.add(new Barang(5, "Buku Tulis", 100, 6000));
    }

    public static void menuUtama() {
        System.out.println("-----------Menu Toko Multiguna-----------");
        System.out.println("1. Pesan Barang");
        System.out.println("2. Lihat Pesanan");
        System.out.println("0. Keluar");
        System.out.print("Pilihan : ");
    }

    public static void tampilBarang() {
        System.out.println("Daftar Barang Toko Multiguna");
        for (Barang b : daftarBarang) {
            System.out.println("ID     : " + b.getId());
            System.out.println("Nama   : " + b.getNama());
            System.out.println("Stock  : " + b.getStock());
            System.out.println("Harga  : " + b.getHarga());
            System.out.println("-------------------------------------");
        }
    }

    public static void pesanBarang() {
        tampilBarang();
        System.out.print("Ketik 0 untuk batal\nPesan Barang (ID) : ");
        int id = sc.nextInt();
        if (id == 0) return;

        Barang pilihBarang = null;
        for (Barang b : daftarBarang) {
            if (b.getId() == id) {
                pilihBarang = b;
                break;
            }
        }

        if (pilihBarang == null) {
            System.out.println("ID Barang Tidak Sesuai Pilihan\n");
            return;
        }

        System.out.print("Masukkan Jumlah : ");
        int jumlah = sc.nextInt();
        if (jumlah <= 0 || jumlah > pilihBarang.getStock()) {
            System.out.println("Jumlah Barang Tidak Sesuai Stock\n");
            return;
        }

        int totalHarga = jumlah * pilihBarang.getHarga();
        System.out.println(jumlah + " @ " + pilihBarang.getNama() + " dengan total harga " + totalHarga);

        System.out.print("Masukkan jumlah uang : ");
        int uang = sc.nextInt();
        if (uang < totalHarga) {
            System.out.println("Jumlah uang tidak mencukupi\n");
            return;
        }

        pilihBarang.minusStock(jumlah);
        daftarPesanan.add(new Order(daftarPesanan.size() + 1, pilihBarang, jumlah));
        System.out.println("Berhasil dipesan\n");
    }

    public static void lihatPesanan() {
        if (daftarPesanan.isEmpty()) {
            System.out.println("Belum ada pesanan.\n");
            return;
        }
        System.out.println("Daftar Pesanan Toko Multiguna");
        for (Order o : daftarPesanan) {
            System.out.println("ID     : " + o.getId());
            System.out.println("Nama   : " + o.getBarang().getNama());
            System.out.println("Jumlah : " + o.getJumlah());
            System.out.println("Total  : " + (o.getJumlah() * o.getBarang().getHarga()));
            System.out.println("-------------------------------------");
        }
    }
}

