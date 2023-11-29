/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actionlistener;

import java.awt.event.*;
import biodata.Biodata;
import biodata.BiodataFrame;
import dao.BiodataDao;

/**
 *
 * Penjelasan Kode: - Package declaration untuk mengorganisir kelas dalam paket
 * "actionlistener". - Mengimpor kelas-kelas yang diperlukan dari paket
 * java.awt.event, biodata, dan dao.
 */
public class SimpanUbahActionListener implements ActionListener {

    private final BiodataFrame biodataFrame;
    private final BiodataDao biodataDao;
    private String id;

    /**
     * Konstruktor untuk kelas SimpanUbahActionListener. biodataFrame Objek
     * BiodataFrame yang akan diubah. biodataDao Objek BiodataDao untuk
     * mengakses data biodata. ID data biodata yang akan diubah.
     */
    public SimpanUbahActionListener(BiodataFrame biodataFrame, BiodataDao biodataDao, String id) {
        this.biodataFrame = biodataFrame;
        this.biodataDao = biodataDao;
        this.id = id;
    }

    /**
     * Metode yang dipanggil ketika aksi terjadi, dalam hal ini, ketika tombol
     * "Simpan/Ubah" diklik.
     *
     * @param e Objek ActionEvent yang mewakili aksi yang terjadi.
     */
    public void actionPerformed(ActionEvent e) {
        // Inisialisasi variabel jenisKelamin sebagai string kosong.
        String jenisKelamin = "";

        // Menentukan jenis kelamin berdasarkan pilihan pada GUI dan mengatur 
        // ulang pilihan.
        if (biodataFrame.getJenisLaki().isSelected()) {
            jenisKelamin = biodataFrame.getJenisLaki().getText();
            biodataFrame.getJenisLaki().setSelected(false);
        } else if (biodataFrame.getJenisPerempuan().isSelected()) {
            jenisKelamin = biodataFrame.getJenisPerempuan().getText();
            biodataFrame.getJenisPerempuan().setSelected(false);
        }

        // Mendapatkan nilai teks dari komponen GUI untuk nama, telepon, dan 
        // alamat.
        String nama = this.biodataFrame.getNamaTextField().getText();
        String telepon = this.biodataFrame.getNoTeleponTextField().getText();
        String alamat = this.biodataFrame.getAlamatTextField().getText();

        // Memeriksa apakah semua input (nama, telepon, dan alamat) kosong, dan 
        // menampilkan peringatan jika iya.
        if (nama.equalsIgnoreCase("") && telepon.equalsIgnoreCase("") && alamat.equalsIgnoreCase("")) {
            this.biodataFrame.showAlertAllEmpty();
            return;
        } else {
            // Memeriksa masing-masing input (nama, telepon, dan alamat) secara 
            // terpisah, dan menampilkan peringatan jika kosong.
            if (nama.equalsIgnoreCase("")) {
                this.biodataFrame.showAlertNameEmpty();
                return;
            }
            if (telepon.equalsIgnoreCase("")) {
                this.biodataFrame.showAlertTelephoneEmpty();
                return;
            }
            if (alamat.equalsIgnoreCase("")) {
                this.biodataFrame.showAlertAddressEmpty();
                return;
            }
        }

        // Menampilkan konfirmasi untuk aksi "Ubah" dan menanggapi tindakan 
        // pengguna.
        int confirmation = this.biodataFrame.showConfirmation("ubah");

        if (confirmation == 0) {
            // Membuat objek Biodata dengan nilai yang akan diubah.
            Biodata biodata = new Biodata();
            biodata.setId(this.id);
            biodata.setNama(nama);
            biodata.setNoTelepon(telepon);
            biodata.setJenisKelamin(jenisKelamin);
            biodata.setAlamat(alamat);

            // Mengupdate data pada BiodataFrame dan di database melalui 
            // BiodataDao.
            this.biodataFrame.updateBiodata(biodata);
            this.biodataDao.update(biodata);

            // Menampilkan pemberitahuan sukses dan mengatur ulang ID.
            this.biodataFrame.showAlertSuccess("diubah");
            this.id = null;
        } else {
            // Menampilkan pemberitahuan gagal jika pengguna membatalkan aksi.
            this.biodataFrame.showAlertFailed("diubah");
        }

        // Mengatur ulang nilai teks pada komponen GUI untuk nama, telepon, dan 
        // alamat.
        this.biodataFrame.getNamaTextField().setText("");
        this.biodataFrame.getNoTeleponTextField().setText("");
        this.biodataFrame.getAlamatTextField().setText("");

        // Menghapus diri sendiri sebagai action listener dari tombol 
        // "Simpan/Ubah".
        this.biodataFrame.getButtonSimpanUbah().removeActionListener(this);
    }
}
