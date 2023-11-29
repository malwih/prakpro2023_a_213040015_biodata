/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actionlistener;

import java.awt.event.*;

import biodata.BiodataFrame;
import dao.BiodataDao;

/**
 *
 * Penjelasan Kode: - Package declaration untuk mengorganisir kelas dalam paket
 * "actionlistener". - Mengimpor kelas-kelas yang diperlukan dari paket
 * java.awt.event, biodata, dan dao.
 */
public class UbahActionListener implements ActionListener {

    private final BiodataFrame biodataFrame;
    private final BiodataDao biodataDao;

    /**
     * Konstruktor untuk kelas UbahActionListener.
     *
     * @param biodataFrame Objek BiodataFrame yang akan diubah.
     * @param biodataDao Objek BiodataDao untuk mengakses data biodata.
     */
    public UbahActionListener(BiodataFrame biodataFrame, BiodataDao biodataDao) {
        this.biodataFrame = biodataFrame;
        this.biodataDao = biodataDao;
    }

    /**
     * Metode yang dipanggil ketika aksi terjadi, dalam hal ini, ketika tombol
     * // "Ubah" diklik.
     *
     * @param e Objek ActionEvent yang mewakili aksi yang terjadi.
     */
    public void actionPerformed(ActionEvent e) {
        // Mendapatkan baris dan kolom terpilih dari tabel pada BiodataFrame.
        int row = this.biodataFrame.getTable().getSelectedRow();
        int column = this.biodataFrame.getTable().getSelectedColumn();

        // Mendapatkan nilai data biodata yang akan diubah.
        String biodataUbah = (String) this.biodataFrame.getTable().getModel().
                getValueAt(row, column);
        String id = "";
        String col = "";

        // Menggunakan switch untuk menentukan kolom yang akan diubah berdasarkan
        // indeks kolom.
        switch (column) {
            case 0:
                col = "nama";
                break;

            case 1:
                col = "no_telepon";
                break;

            case 2:
                col = "jenis_kelamin";
                break;

            case 3:
                col = "alamat";
                break;

            default:
                System.out.println("Kolom tidak ditemukan");
                break;
        }

        // Mengambil ID dari data biodata yang akan diubah.
        id = this.biodataDao.select(col, biodataUbah).getId();

        // Mengatur nilai teks pada komponen GUI sesuai dengan data biodata yang 
        // akan diubah.
        this.biodataFrame.getNamaTextField().setText(this.biodataDao.select(col, biodataUbah).getNama());
        this.biodataFrame.getNoTeleponTextField().setText(this.biodataDao.
                select(col, biodataUbah).getNoTelepon());

        // Menetapkan pilihan jenis kelamin pada GUI berdasarkan data biodata 
        // yang akan diubah.
        if (this.biodataDao.select(col, biodataUbah).getJenisKelamin().equals("Laki-Laki")) {
            this.biodataFrame.getJenisLaki().setSelected(true);
        } else {
            this.biodataFrame.getJenisPerempuan().setSelected(true);
        }

        // Mengatur nilai teks pada komponen GUI untuk alamat berdasarkan data 
        // biodata yang akan diubah.
        this.biodataFrame.getAlamatTextField().setText(this.biodataDao.select(col, biodataUbah).getAlamat());

        // Membuat objek SimpanUbahActionListener dan menambahkannya sebagai 
        // listener untuk tombol Simpan/Ubah.
        SimpanUbahActionListener simpanUbahListener = new SimpanUbahActionListener(
                this.biodataFrame,
                this.biodataDao,
                id);

        this.biodataFrame.getButtonSimpanUbah().addActionListener(simpanUbahListener);
    }
}
