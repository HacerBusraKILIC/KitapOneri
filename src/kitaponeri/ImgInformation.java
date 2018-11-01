/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kitaponeri;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ImgInformation {
    private ImageIcon img;
    private String bookTitle;
    private String bookAuthor;
    private String yearOfPublication;
    private String urlS;
    private String urlM;
    private String urlL;

     public ImgInformation(ImageIcon icon, String bookTitle){
        this.img = icon;
        this.bookTitle = bookTitle;
    }
     
    public ImgInformation(ImageIcon icon, String bookTitle, String bookAuthor, String yearOfPublication){
        this.img = icon;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.yearOfPublication = yearOfPublication;
        this.urlM = urlM;
    }
    
    
    public Icon getImg() {
        return img;
    }

    public void setImg(ImageIcon img) {
        this.img = img;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(String yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public String getUrlS() {
        return urlS;
    }

    public void setUrlS(String urlS) {
        this.urlS = urlS;
    }

    public String getUrlM() {
        return urlM;
    }

    public void setUrlM(String urlM) {
        this.urlM = urlM;
    }

    public String getUrlL() {
        return urlL;
    }

    public void setUrlL(String urlL) {
        this.urlL = urlL;
    }
    
    
    
}
