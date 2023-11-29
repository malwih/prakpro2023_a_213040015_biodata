/*
 * Kelas Main merupakan kelas utama yang berfungsi untuk menjalankan aplikasi dan menampilkan antarmuka pengguna.
 */
package main;

import javax.swing.*;
import dao.BiodataDao;
import biodata.BiodataFrame;

public class Main extends JFrame {

    // Objek BiodataDao untuk berinteraksi dengan database
    private final BiodataDao biodataDao;

    // Objek BiodataFrame untuk menampilkan antarmuka pengguna
    private final BiodataFrame biodataFrame;

    // Konstruktor kelas Main
    public Main() {
        this.setTitle("Biodata"); // Judul frame

        this.setSize(400, 500); // Ukuran frame

        // Membuat objek BiodataDao
        this.biodataDao = new BiodataDao();

        // Membuat objek BiodataFrame dengan menggunakan objek BiodataDao
        this.biodataFrame = new BiodataFrame(biodataDao);

        // Menjadikan frame BiodataFrame terlihat
        this.biodataFrame.setVisible(true);
    }

    // Metode main untuk menjalankan aplikasi
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}
