/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Menentukan paket atau package dari kelas Biodata
package biodata;

/**
 * Kelas Biodata digunakan untuk merepresentasikan data biodata seseorang. Kelas
 * ini memiliki properti seperti id, nama, nomor telepon, jenis kelamin, dan
 * alamat. Kelas ini juga menyediakan metode-metode untuk mengatur dan mengambil
 * nilai dari properti-propertinya.
 *
 * @author malwi
 */
public class Biodata {

    // Properti-propertri atau atribut-atribut dari kelas Biodata
    private String id;
    private String nama;
    private String noTelepon;
    private String jenisKelamin;
    private String alamat;

    // Metode untuk mengatur nilai id
    public void setId(String id) {
        this.id = id;
    }

    // Metode untuk mengambil nilai id
    public String getId() {
        return this.id;
    }

    // Metode untuk mengatur nilai nama
    public void setNama(String nama) {
        this.nama = nama;
    }

    // Metode untuk mengambil nilai nama
    public String getNama() {
        return this.nama;
    }

    // Metode untuk mengatur nilai nomor telepon
    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    // Metode untuk mengambil nilai nomor telepon
    public String getNoTelepon() {
        return this.noTelepon;
    }

    // Metode untuk mengatur nilai jenis kelamin
    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    // Metode untuk mengambil nilai jenis kelamin
    public String getJenisKelamin() {
        return this.jenisKelamin;
    }

    // Metode untuk mengatur nilai alamat
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    // Metode untuk mengambil nilai alamat
    public String getAlamat() {
        return this.alamat;
    }
}
