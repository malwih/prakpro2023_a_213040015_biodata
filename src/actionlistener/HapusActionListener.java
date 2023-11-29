package actionlistener;

import java.awt.event.*;

import biodata.Biodata;
import biodata.BiodataFrame;
import dao.BiodataDao;

// Mendefinisikan kelas HapusActionListener yang mengimplementasikan
// ActionListener
public class HapusActionListener implements ActionListener {

    // Variabel instance untuk menyimpan referensi ke BiodataFrame dan BiodataDao
    private final BiodataFrame biodataFrame;
    private final BiodataDao biodataDao;

    // Konstruktor kelas HapusActionListener dengan parameter BiodataFrame dan
    // BiodataDao
    public HapusActionListener(BiodataFrame biodataFrame, BiodataDao biodataDao) {
        this.biodataFrame = biodataFrame;
        this.biodataDao = biodataDao;
    }

    // Metode yang dipanggil ketika aksi hapus dilakukan
    public void actionPerformed(ActionEvent e) {

        // Mendapatkan indeks baris dan kolom terpilih dari tabel
        int row = this.biodataFrame.getTable().getSelectedRow();
        int column = this.biodataFrame.getTable().getSelectedColumn();

        // Memeriksa apakah baris atau kolom tidak terpilih
        if (row == -1 || column == -1) {
            this.biodataFrame.showAlertFailed("dihapus");
            return;
        } else {
            // Mendapatkan nilai dari sel terpilih dan membuat objek Biodata
            // untuk mencari ID
            String newValue = (String) this.biodataFrame.getTable().getModel()
                    .getValueAt(row, column);
            Biodata id = new Biodata();
            String col = "";

            // Menentukan kolom yang sesuai dengan kolom terpilih
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

            // Menggunakan BiodataDao untuk mencari Biodata berdasarkan kolom
            // dan nilai
            id = this.biodataDao.select(col, newValue);

            // Menampilkan dialog konfirmasi hapus
            int confirmation = this.biodataFrame.showConfirmation("hapus");

            // Jika pengguna memilih untuk tidak menghapus, tampilkan pesan
            // gagal dan kembali
            if (confirmation == 1) {
                this.biodataFrame.showAlertFailed("tidak dihapus");
                return;
            } else {
                // Menghapus Biodata dari BiodataFrame dan BiodataDao, serta
                // menampilkan pesan sukses
                this.biodataFrame.deleteBiodata(id);
                this.biodataDao.delete(id);
                this.biodataFrame.showAlertSuccess("dihapus");
            }
        }
    }
}
