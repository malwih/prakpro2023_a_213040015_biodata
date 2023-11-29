package actionlistener;

import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import biodata.BiodataFrame;

// Mendefinisikan kelas CloseWindowActionListener yang mengimplementasikan
//WindowListener
public class CloseWindowActionListener implements WindowListener {

    // Variabel instance untuk menyimpan referensi ke BiodataFrame
    private final BiodataFrame biodataFrame;

    // Konstruktor kelas CloseWindowActionListener dengan parameter BiodataFrame
    public CloseWindowActionListener(BiodataFrame biodataFrame) {
        this.biodataFrame = biodataFrame;
    }

    // Metode yang dipanggil ketika jendela sedang ditutup
    public void windowClosing(WindowEvent e) {

        // Menampilkan dialog konfirmasi dengan opsi Ya/Tidak
        int confirmation = JOptionPane.showConfirmDialog(this.biodataFrame,
                "Yakin ingin keluar aplikasi?\nSemua data yang belum "
                + "disimpan, tidak akan tersimpan.",
                "Form Biodata",
                JOptionPane.YES_NO_OPTION);

        // Jika pengguna memilih Ya, keluar dari aplikasi
        if (confirmation == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            // Jika pengguna memilih Tidak, set tindakan default untuk tombol
            //tutup jendela
            this.biodataFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }

    // Metode-metode berikut ini perlu diimplementasikan karena kelas
    // mengimplementasikan WindowListener
    public void windowOpened(WindowEvent e) {
        // Tidak ada tindakan yang perlu diambil saat jendela dibuka
    }

    public void windowClosed(WindowEvent e) {
        // Tidak ada tindakan yang perlu diambil saat jendela ditutup
    }

    public void windowIconified(WindowEvent e) {
        // Tidak ada tindakan yang perlu diambil saat jendela dikecilkan
    }

    public void windowDeiconified(WindowEvent e) {
        // Tidak ada tindakan yang perlu diambil saat jendela dikembalikan dari
        // keadaan terkecil
    }

    public void windowActivated(WindowEvent e) {
        // Tidak ada tindakan yang perlu diambil saat jendela diaktifkan
    }

    public void windowDeactivated(WindowEvent e) {
        // Tidak ada tindakan yang perlu diambil saat jendela dinonaktifkan
    }
}
