/*
 * Kelas BiodataDao adalah kelas yang bertanggung jawab untuk berinteraksi 
    dengan database
 * dan melakukan operasi CRUD (Create, Read, Update, Delete) terhadap data 
    biodata.
 */
package dao;

import db.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import biodata.Biodata;

public class BiodataDao {

    // Metode untuk menyimpan data biodata ke dalam database
    public int insert(Biodata biodata) {
        int result = -1;

        try (Connection connection = MySqlConnection.getInstance().
                getConnection()) {
            // Menyiapkan statement SQL untuk operasi insert
            PreparedStatement statement = connection.prepareStatement(
                    "Insert into biodata(id, nama, no_telepon, "
                    + "jenis_kelamin, alamat) values (?, ?, ?, ?, ?)");

            // Mengisi parameter pada statement SQL dengan nilai dari objek 
            // biodata
            statement.setString(1, biodata.getId());
            statement.setString(2, biodata.getNama());
            statement.setString(3, biodata.getNoTelepon());
            statement.setString(4, biodata.getJenisKelamin());
            statement.setString(5, biodata.getAlamat());

            // Mengeksekusi statement SQL
            result = statement.executeUpdate();

            // Menampilkan informasi di konsol
            System.out.println("Insert data: " + biodata.getId() + " "
                    + biodata.getNama() + " "
                    + biodata.getNoTelepon() + " " + biodata.getJenisKelamin()
                    + " " + biodata.getAlamat());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    // Metode untuk mengupdate data biodata di dalam database
    public int update(Biodata biodata) {
        int result = -1;

        try (Connection connection = MySqlConnection.getInstance().
                getConnection()) {
            // Menyiapkan statement SQL untuk operasi update
            PreparedStatement statement = connection.prepareStatement(
                    "update biodata set nama = ?, no_telepon = ?, "
                    + "jenis_kelamin = ?, alamat = ? where id = ?");

            // Mengisi parameter pada statement SQL dengan nilai dari objek 
            // biodata
            statement.setString(1, biodata.getNama());
            statement.setString(2, biodata.getNoTelepon());
            statement.setString(3, biodata.getJenisKelamin());
            statement.setString(4, biodata.getAlamat());
            statement.setString(5, biodata.getId());

            // Mengeksekusi statement SQL
            result = statement.executeUpdate();

            // Menampilkan informasi di konsol
            System.out.println("Update data: " + biodata.getId() + " "
                    + biodata.getNama() + " "
                    + biodata.getNoTelepon() + " " + biodata.getJenisKelamin()
                    + " " + biodata.getAlamat());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Metode untuk menghapus data biodata dari database
    public int delete(Biodata biodata) {
        int result = -1;

        try (Connection connection = MySqlConnection.getInstance().
                getConnection()) {
            // Menyiapkan statement SQL untuk operasi delete
            PreparedStatement statement = connection.prepareStatement(
                    "delete from biodata where id = ?");

            // Mengisi parameter pada statement SQL dengan nilai dari objek 
            // biodata
            statement.setString(1, biodata.getId());

            // Mengeksekusi statement SQL
            result = statement.executeUpdate();

            // Menampilkan informasi di konsol
            System.out.println("Delete data: " + biodata.getId() + " "
                    + biodata.getNama() + " "
                    + biodata.getNoTelepon() + " " + biodata.getJenisKelamin()
                    + " " + biodata.getAlamat());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Metode untuk mengambil semua data biodata dari database
    public List<Biodata> findAll() {
        List<Biodata> list = new ArrayList<>();

        try (
                Connection connection = MySqlConnection.getInstance().
                        getConnection(); Statement statement = connection.createStatement();) {

                    // Mengeksekusi statement SQL untuk mendapatkan semua data biodata
                    try (ResultSet resultSet = statement.executeQuery(
                            "select * from biodata order by nama asc")) {
                        while (resultSet.next()) {
                            Biodata biodata = new Biodata();

                            // Mengisi objek biodata dengan nilai dari hasil query
                            biodata.setId(resultSet.getString("id"));
                            biodata.setNama(resultSet.getString("nama"));
                            biodata.setNoTelepon(resultSet.getString(
                                    "no_telepon"));
                            biodata.setJenisKelamin(resultSet.getString(
                                    "jenis_kelamin"));
                            biodata.setAlamat(resultSet.getString("alamat"));

                            // Menambahkan objek biodata ke dalam list
                            list.add(biodata);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return list;
    }

    // Metode untuk mengambil satu data biodata dari database berdasarkan kolom 
    // dan nilai tertentu
    public Biodata select(String column, String value) {
        Biodata biodata = new Biodata();

        try (
                Connection connection = MySqlConnection.getInstance().
                        getConnection(); Statement statement = connection.createStatement();) {
                    // Mengeksekusi statement SQL untuk mendapatkan satu data biodata
                    try (ResultSet resultSet = statement.executeQuery("select * "
                            + "from biodata where " + column + " = '" + value + "'");) {
                        while (resultSet.next()) {
                            // Mengisi objek biodata dengan nilai dari hasil query
                            biodata.setId(resultSet.getString("id"));
                            biodata.setNama(resultSet.getString("nama"));
                            biodata.setNoTelepon(resultSet.getString(
                                    "no_telepon"));
                            biodata.setJenisKelamin(resultSet.getString(
                                    "jenis_kelamin"));
                            biodata.setAlamat(resultSet.getString("alamat"));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return biodata;
    }
}
