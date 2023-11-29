/*
 * Kelas ModelTable merupakan kelas yang mengimplementasikan AbstractTableModel dari Java Swing.
 * Kelas ini digunakan untuk mengatur model data yang akan ditampilkan di JTable pada antarmuka pengguna.
 */
package biodata;

import javax.swing.table.*;
import java.util.List;

public class ModelTable extends AbstractTableModel {

    // Array berisi nama-nama kolom pada tabel
    private String[] columnNames = {"Nama", "Nomor HP", "Jenis Kelamin",
        "Alamat"};

    // List data biodata yang akan ditampilkan di tabel
    private List<Biodata> data;

    // Konstruktor kelas ModelTable
    public ModelTable(List<Biodata> data) {
        this.data = data;
    }

    // Metode untuk mendapatkan jumlah kolom pada tabel
    public int getColumnCount() {
        return columnNames.length;
    }

    // Metode untuk mendapatkan jumlah baris pada tabel
    public int getRowCount() {
        return data.size();
    }

    // Metode untuk mendapatkan nama kolom pada indeks tertentu
    public String getColumnName(int col) {
        return columnNames[col];
    }

    // Metode untuk mendapatkan nilai pada sel tabel pada baris dan kolom 
    // tertentu
    public Object getValueAt(int row, int col) {
        Biodata rowItem = data.get(row);
        String value = "";

        // Switch case untuk menentukan nilai berdasarkan kolom
        switch (col) {
            case 0:
                value = rowItem.getNama();
                break;

            case 1:
                value = rowItem.getNoTelepon();
                break;

            case 2:
                value = rowItem.getJenisKelamin();
                break;

            case 3:
                value = rowItem.getAlamat();
                break;
        }

        return value;
    }

    // Metode untuk menentukan apakah sel pada tabel dapat diubah atau tidak
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    // Metode untuk menambahkan data biodata ke tabel
    public void add(Biodata value) {
        data.add(value);
        // Memperbarui tampilan JTable setelah penambahan data
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    // Metode untuk mengupdate data biodata di tabel
    public void update(Biodata value) {
        int i = 0;

        // Mencari dan mengganti data biodata yang sesuai dengan ID
        for (Biodata b : data) {
            if (b.getId().equals(value.getId())) {
                b = value;
                data.set(i, value);
                // Memperbarui tampilan JTable setelah pembaruan data
                fireTableCellUpdated(data.size() - 1, data.size() - 1);
            }
            i++;
        }
    }

    // Metode untuk menghapus data biodata dari tabel
    public void delete(Biodata value) {
        int i = 0;

        // Mencari dan menghapus data biodata yang sesuai dengan ID
        for (Biodata b : data) {
            if (b.getId().equals(value.getId())) {
                data.remove(i);
                break;
            }
            i++;
        }

        // Memperbarui tampilan JTable setelah penghapusan data
        fireTableRowsDeleted(data.size() - 1, data.size() - 1);
    }
}
