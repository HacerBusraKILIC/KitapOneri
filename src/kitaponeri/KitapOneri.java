/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kitaponeri;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JFrame;
import static kitaponeri.uyelikSayfasi.DB_URL;

public class KitapOneri {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/kitapOneriKullanici";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "1038";

    private static String SELECT_QUERY = "SELECT * FROM `kullanicilar`";
    private static String INSERT_QUERY_UYELIK = "INSERT INTO `bx_users` "
            + "(`user_id`, `location`, `age`) VALUES "
            + "(?,?,?)";
    private static String DELETE_QUERY = "DELETE FROM `kullanicilar` "
            + "WHERE `kullanicilar`.`ID` = ?";
    private static Connection con;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        loginSayfasi uye = new loginSayfasi();
        uye.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //programi kapatmak için gereken kod.
        uye.setSize(500, 500);
        uye.setVisible(true);

        File file = new File("BX-Users.csv");
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(file));
        String satir = reader.readLine();
        System.out.println(satir);

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            con = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);

            //int i = 0;
            
            /*while (satir != null) {    //veri tabanına BX-USERS dosyasındaki verilerin eklenmesi
                //System.out.println("i---->" +i);
                satir = reader.readLine();
                String[] temp = satir.split(";");

                int boyut = temp.length;
                for (int j = 0; j < boyut; j++) {
                    //System.out.println("--->  " + j + temp[j]);
                }
                //i++;
                //if(i > 275394){
                uyeEkle(Integer.valueOf(temp[0]), temp[1], temp[2]);
                //}
            }*/
            
            
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } 
    }

    private static void uyeEkle(int user_id, String location, String age) throws SQLException {
        PreparedStatement prpstmt = null;

        prpstmt = con.prepareStatement(INSERT_QUERY_UYELIK);
        prpstmt.setInt(1, user_id);
        prpstmt.setString(2, location);
        prpstmt.setString(3, age);
        // sorguyu gönder
        prpstmt.executeUpdate();

        System.out.println("\n***" + user_id + " id'li kullanıcı eklendi.");
    }
        
    public static void yazdir(String[] temp) {
        for (int i = 0; i < temp.length; i++) {
            System.out.println(temp[i]);
        }
    }

}
