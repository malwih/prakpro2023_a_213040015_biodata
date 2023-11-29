package actionlistener;

import java.util.List;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import biodata.BiodataFrame;
import biodata.Biodata;

// Mendefinisikan kelas SaveToFileActionListener yang mengimplementasikan
// ActionListener
public class SaveToFileActionListener implements ActionListener {

    // Variabel instance untuk menyimpan referensi ke BiodataFrame dan List
    // Biodata
    private final BiodataFrame biodataFrame;
    private final List<Biodata> biodataList;

    // Konstruktor kelas SaveToFileActionListener dengan parameter BiodataFrame
    // dan List Biodata
    public SaveToFileActionListener(BiodataFrame biodataFrame, List<Biodata> biodataList) {
        this.biodataFrame = biodataFrame;
        this.biodataList = biodataList;
    }

    // Metode yang dipanggil ketika aksi penyimpanan ke file dilakukan
    public void actionPerformed(ActionEvent e) {

        // Menampilkan dialog konfirmasi untuk menyimpan data ke file
        int confirmation = JOptionPane.showConfirmDialog(this.biodataFrame,
                "Apakah anda yakin ingin menyimpan data ke file?",
                "Form Biodata",
                JOptionPane.YES_NO_OPTION);

        // Jika pengguna memilih Ya pada dialog konfirmasi
        if (confirmation == JOptionPane.YES_OPTION) {
            // Membuat objek JFileChooser untuk memilih lokasi penyimpanan file
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Simpan Data ke File");
            fileChooser.setFileFilter(new FileNameExtensionFilter("File"
                    + "Teks", "txt"));

            // Menampilkan dialog pemilihan lokasi penyimpanan file
            int userSelection = fileChooser.showSaveDialog(this.biodataFrame);

            // Jika pengguna memilih lokasi penyimpanan file
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                try {
                    // Membuat objek FileWriter untuk menulis data ke file
                    FileWriter writer = new FileWriter(fileChooser.
                            getSelectedFile());

                    // Iterasi melalui List Biodata untuk menulis setiap entri 
                    // ke file
                    for (int i = 0; i < biodataList.size(); i++) {
                        // Menuliskan data ke file, memisahkan setiap atribut dengan
                        // koma
                        writer.write(biodataList.get(i).getNama() + ",");
                        writer.write(biodataList.get(i).getNoTelepon() + ",");
                        writer.write(biodataList.get(i).getJenisKelamin() + ",");
                        writer.write(biodataList.get(i).getAlamat());

                        // Jika bukan entri terakhir, tambahkan karakter newline
                        if (i < biodataList.size() - 1) {
                            writer.write("\n");
                        }
                    }

                    // Menutup FileWriter setelah selesai menulis
                    writer.close();

                    // Menampilkan pesan bahwa data berhasil disimpan ke file
                    JOptionPane.showMessageDialog(this.biodataFrame,
                            "Data berhasil disimpan ke file",
                            "Perhatian",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
