/*
 * Kelas MySqlConnection bertanggung jawab untuk menyediakan koneksi ke database MySQL.
 * Kelas ini mengimplementasikan pola desain Singleton untuk memastikan hanya ada satu instance yang dibuat.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnection {

    // Informasi koneksi ke database MySQL
    private final static String DB_URL = "jdbc:mysql://localhost:3306/biodata";
    private final static String DB_USER = "root";
    private final static String DB_PASS = "";

    // Variabel instance untuk menerapkan pola Singleton
    private static MySqlConnection instance;

    // Metode getInstance untuk mendapatkan instance MySqlConnection
    public static MySqlConnection getInstance() {
        if (instance == null) {
            instance = new MySqlConnection();
        }
        // Mengembalikan nilai instance
        return instance;
    }

    // Metode untuk mendapatkan koneksi ke database
    public Connection getConnection() {
        Connection connection = null;

        try {
            // Memuat driver JDBC untuk MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Membuat koneksi dengan database menggunakan informasi yang 
            // diberikan
            connection = DriverManager.getConnection(DB_URL, DB_USER,
                    DB_PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
