/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kitaponeri;

import com.mysql.jdbc.Connection;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.Renderer;
import static kitaponeri.uyelikSayfasi.DB_URL;

/**
 *
 * @author dell
 */
public class uyelikDevamPage extends javax.swing.JFrame {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/kitapOneriKullanici";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "1038";
    private static Connection con;
    private static Statement st;
    private static ResultSet rs;
    
    private static String INSERT_QUERY_BOOKRATE = "INSERT INTO `bx_book_ratings` "
            + "(`user_id`, `isbn`, `book_rating`) VALUES "
            + "(?,?,?)";

    long goruntulenenSayfa = 1, toplamSayfa = 0, sayac = 0, sayfaItem = 30;
    int addBookCount = 0;

    DefaultListModel dm = new DefaultListModel();

    public uyelikDevamPage() throws IOException {
        initComponents();
        countBook();
        if (sayac % sayfaItem == 0) {
            toplamSayfa = sayac / sayfaItem;
        } else {
            toplamSayfa = sayac / sayfaItem + 1;
        }
        veriCek(1);
        lbgoruntulenenSayfa.setText("1");
        lbsayfaOranı.setText("1/" + toplamSayfa);

    }

    private void veriCek(long goruntulenenSayfa) throws MalformedURLException, IOException {
        bookImgList.removeAll();
        
        try {
            goruntulenenSayfa--;
            String ıtemLimit = "SELECT *FROM bx_books LIMIT " +(goruntulenenSayfa*sayfaItem)+ "," +sayfaItem;
            con = con = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
            st = con.createStatement();
            rs = st.executeQuery(ıtemLimit);

            dm.clear();

            while (rs.next()) {
                //System.out.println("2> "+rs.getString(2)+ "3> "+rs.getString(3)+ "4> "+rs.getString(4)+ "5> "+rs.getString(5));
                //String isbn = rs.getString(1);
                String bookTitle = rs.getString(2);
                String bookAuthor = rs.getString(3);
                String yearOfPublication = rs.getString(4);
                String imgUrlS = rs.getString(6);
                String imgUrlM = rs.getString(7); //System.out.println(rs.getString(7));
                URL url = new URL(imgUrlS);
                BufferedImage image = ImageIO.read(url);
                if(image.getHeight()<50 && image.getWidth()<50){
                    dm.addElement(new ImgInformation(new ImageIcon("warning.png"), bookTitle +"     "+ bookAuthor +"     "+ yearOfPublication));
                }else{
                    dm.addElement(new ImgInformation(new ImageIcon(image),  bookTitle +"     "+ bookAuthor +"     "+ yearOfPublication));
                }

                bookImgList.setCellRenderer(new ImgRenderer());
                bookImgList.setModel(dm);
            }

            /*rs.close();
             st.close();
             cn.close();*/
        } catch (SQLException ex) {
            Logger.getLogger(uyelikDevamPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void countBook() {
        try {
            String query = "Select count(*) from bx_books";
            con = con = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                sayac = rs.getLong(1);
            }
            /*rs.close();
            st.close();
            con.close();*/
        } catch (SQLException ex) {
            Logger.getLogger(uyelikDevamPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void bookRateEkle(String user_id, String isbn, int book_rating) throws SQLException {
        PreparedStatement prpstmt = null;

        prpstmt = con.prepareStatement(INSERT_QUERY_BOOKRATE);
        prpstmt.setString(1, user_id);
        prpstmt.setString(2, isbn);
        prpstmt.setInt(3, book_rating);
        // sorguyu gönder
        prpstmt.executeUpdate();

        //System.out.println("\n***" + user_id + " id'li kullanıcı eklendi.");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bookImgList = new javax.swing.JList();
        oncekiSayfa = new javax.swing.JButton();
        sonrakiSayfa = new javax.swing.JButton();
        lbgoruntulenenSayfa = new javax.swing.JLabel();
        lbsayfaOranı = new javax.swing.JLabel();
        uyeligiTamamla = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("Lütfen en az 10 kitabı oylayarak  uyeliginizi tamalayınız. Sectiginiz kitaba asagıdaki combo box da bulunan numaralardan oy verebilirisiniz.");

        bookImgList.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 12)); // NOI18N
        bookImgList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookImgListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(bookImgList);

        oncekiSayfa.setText("<");
        oncekiSayfa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oncekiSayfaActionPerformed(evt);
            }
        });

        sonrakiSayfa.setText(">");
        sonrakiSayfa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sonrakiSayfaActionPerformed(evt);
            }
        });

        lbgoruntulenenSayfa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbgoruntulenenSayfa.setText("jLabel2");

        lbsayfaOranı.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbsayfaOranı.setText("jLabel2");

        uyeligiTamamla.setText("Uyeligi Tamamla");
        uyeligiTamamla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uyeligiTamamlaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(oncekiSayfa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbgoruntulenenSayfa)
                        .addGap(18, 18, 18)
                        .addComponent(sonrakiSayfa)
                        .addGap(18, 18, 18)
                        .addComponent(lbsayfaOranı)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(uyeligiTamamla)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addContainerGap(298, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(oncekiSayfa)
                    .addComponent(sonrakiSayfa)
                    .addComponent(lbgoruntulenenSayfa)
                    .addComponent(lbsayfaOranı)
                    .addComponent(uyeligiTamamla))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sonrakiSayfaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sonrakiSayfaActionPerformed
        // TODO add your handling code here:
        if (toplamSayfa > goruntulenenSayfa) {
            goruntulenenSayfa++;
            try {
                veriCek(goruntulenenSayfa);
            } catch (IOException ex) {
                Logger.getLogger(uyelikDevamPage.class.getName()).log(Level.SEVERE, null, ex);
            }
            lbgoruntulenenSayfa.setText("" + goruntulenenSayfa);
            lbsayfaOranı.setText(goruntulenenSayfa + "/" + toplamSayfa);
        }
    }//GEN-LAST:event_sonrakiSayfaActionPerformed

    private void oncekiSayfaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oncekiSayfaActionPerformed
        // TODO add your handling code here:
        if (goruntulenenSayfa > 1) {
            goruntulenenSayfa--;
            try {
                veriCek(goruntulenenSayfa);
            } catch (IOException ex) {
                Logger.getLogger(uyelikDevamPage.class.getName()).log(Level.SEVERE, null, ex);
            }
            lbgoruntulenenSayfa.setText("" + goruntulenenSayfa);
            lbsayfaOranı.setText(goruntulenenSayfa + "/" + toplamSayfa);
        }
    }//GEN-LAST:event_oncekiSayfaActionPerformed

    private void bookImgListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookImgListMouseClicked
        //JOptionPane.showMessageDialog(null, ((ImgInformation) bookImgList.getSelectedValue()).getBookTitle());
        //System.out.println("listItem:   " + ((ImgInformation) bookImgList.getSelectedValue()).getBookTitle());
        String[] rate = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        JComboBox<String> rateValue = new JComboBox<>(rate);
        // user_id alınıp book rate e veri eklenecek bunun icin split ile ayrıistirildi
        String book_isbn = null, book_userId= null;
        String[] selectItem = ( (ImgInformation) bookImgList.getSelectedValue()).getBookTitle().split("     ");
        String selectIsbn = "SELECT bx_books.isbn FROM bx_books WHERE bx_books.book_title=\"" 
                            +selectItem[0]+ "\" AND " +"bx_books.book_author=\"" 
                            +selectItem[1] + "\" AND " +"bx_books.year_of_publication=\"" 
                            +selectItem[2] +"\"";
        System.out.println("rate ekle sorgu: "+selectIsbn);
        try {
            st = con.prepareStatement(selectIsbn);
            rs = st.executeQuery(selectIsbn);
            while (rs.next()) {
                book_isbn = rs.getString(1);
                //System.out.println("selectIsbn try: " + book_isbn);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(uyelikDevamPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, rateValue, "Lütfen oylamanizi yapiniz", WIDTH);
        
        String find_userId = "SELECT  bx_users.user_id  FROM bx_users ORDER BY bx_users.user_id DESC LIMIT 1"; 
        try {
            st = con.prepareStatement(find_userId);
            rs = st.executeQuery(find_userId);
            while (rs.next()) {
                book_userId = rs.getString(1);
                //System.out.println("find_userId try: " + book_userId);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(uyelikDevamPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            int rateEkle = Integer.parseInt((String) rateValue.getSelectedItem());
            bookRateEkle(book_userId, book_isbn, rateEkle);
            addBookCount++; //System.out.println("addBookCount++; " +addBookCount);
        } catch (SQLException ex) {
            Logger.getLogger(uyelikDevamPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
    }//GEN-LAST:event_bookImgListMouseClicked

    private void uyeligiTamamlaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uyeligiTamamlaActionPerformed
        if(addBookCount <= 10){
                JOptionPane.showMessageDialog(null, "Uyeliginiz tamamlanmıstır. Ana sayfadan devam edebilirsiniz.");
                anaSayfa anasayfa;
            try {
                anasayfa = new anaSayfa();
                anasayfa.setSize(700, 1000);
                anasayfa.setVisible(true);
                this.setVisible(false);
            } catch (IOException ex) {
                Logger.getLogger(uyelikDevamPage.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            }else if(addBookCount < 10){
                JOptionPane.showMessageDialog(null, "Lütfen en az 10 kitabı oylayarak  uyeliginizi tamalayınız.");
            }
    }//GEN-LAST:event_uyeligiTamamlaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(uyelikDevamPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(uyelikDevamPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(uyelikDevamPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(uyelikDevamPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new uyelikDevamPage().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(uyelikDevamPage.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList bookImgList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbgoruntulenenSayfa;
    private javax.swing.JLabel lbsayfaOranı;
    private javax.swing.JButton oncekiSayfa;
    private javax.swing.JButton sonrakiSayfa;
    private javax.swing.JButton uyeligiTamamla;
    // End of variables declaration//GEN-END:variables
}
