/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actionlistener;

import java.awt.event.*;
import java.util.UUID;
import biodata.Biodata;
import biodata.BiodataFrame;
import dao.BiodataDao;

/**
 * Kelas SimpanActionListener adalah ActionListener yang menangani aksi simpan
 * data biodata.
 *
 * Kelas ini mengimplementasikan interface ActionListener.
 */
public class SimpanActionListener implements ActionListener {

    // Variabel instance untuk menyimpan referensi ke BiodataFrame dan BiodataDao
    private final BiodataFrame biodataFrame;
    private final BiodataDao biodataDao;

    // Konstruktor kelas SimpanActionListener dengan parameter BiodataFrame dan 
    // BiodataDao
    public SimpanActionListener(BiodataFrame biodataFrame, BiodataDao biodataDao) {
        this.biodataFrame = biodataFrame;
        this.biodataDao = biodataDao;
    }

    // Metode yang dipanggil ketika aksi simpan dilakukan
    public void actionPerformed(ActionEvent e) {

        // Mendapatkan jenis kelamin dari radio button yang dipilih
        String jenisKelamin = "";

        if (biodataFrame.getJenisLaki().isSelected()) {
            jenisKelamin = biodataFrame.getJenisLaki().getText();
            biodataFrame.getJenisLaki().setSelected(false);
        }

        if (biodataFrame.getJenisPerempuan().isSelected()) {
            jenisKelamin = biodataFrame.getJenisPerempuan().getText();
            biodataFrame.getJenisPerempuan().setSelected(false);
        }

        // Mendapatkan nilai nama, telepon, dan alamat dari BiodataFrame
        String nama = biodataFrame.getNama();
        String telepon = biodataFrame.getNoTelepon();
        String alamat = biodataFrame.getAlamat();

        // Memeriksa apakah semua input kosong
        if (nama.equalsIgnoreCase("") && telepon.equalsIgnoreCase("") && alamat.equalsIgnoreCase("")) {
            this.biodataFrame.showAlertAllEmpty();
            return;
        } else {
            // Memeriksa setiap input secara terpisah dan menampilkan pesan 
            // kesalahan jika diperlukan
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

        // Menampilkan dialog konfirmasi untuk menambahkan biodata
        int confirmation = this.biodataFrame.showConfirmation("tambah");

        // Jika pengguna mengonfirmasi untuk menambahkan biodata
        if (confirmation == 0) {
            // Membuat objek Biodata baru dan mengisi atributnya
            Biodata biodata = new Biodata();
            biodata.setId(UUID.randomUUID().toString());
            biodata.setNama(nama);
            biodata.setNoTelepon(telepon);
            biodata.setJenisKelamin(jenisKelamin);
            biodata.setAlamat(alamat);

            // Menambahkan biodata ke BiodataFrame dan menyimpannya ke database 
            // melalui BiodataDao
            this.biodataFrame.addBiodata(biodata);
            this.biodataDao.insert(biodata);

            // Menampilkan pesan sukses
            this.biodataFrame.showAlertSuccess("ditambahkan");
        } else {
            // Jika pengguna membatalkan penambahan biodata, menampilkan pesan 
            // gagal
            this.biodataFrame.showAlertFailed("ditambahkan");
        }

        // Mengosongkan input pada BiodataFrame setelah aksi simpan selesai
        this.biodataFrame.getNamaTextField().setText("");
        this.biodataFrame.getNoTeleponTextField().setText("");
        this.biodataFrame.getAlamatTextField().setText("");
    }
}
