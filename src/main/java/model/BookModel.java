package model;

import view.NewRecordWindow;

/**
 * Created by ivand on 18.03.2018.
 */
public class BookModel {

    private String title;
    private String author;
    private String dateOfPublication;
    private int isRead;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDateOfPublication() {
        return dateOfPublication;
    }

    public int isRead() {
        return isRead;
    }

    public void setTitle() {
        this.title = NewRecordWindow.getTitleTextField().getText();
    }

    public void setAuthor() {
        this.author = NewRecordWindow.getAuthorTextField().getText();
    }

    public void setDateOfPublication() {
        this.dateOfPublication = NewRecordWindow.getResultDate();
    }

    public void setRead() {
        this.isRead = readCheckBoxValue();
    }

    private int readCheckBoxValue() {
        if (NewRecordWindow.getIsReadCheckBox().isSelected()) {
            return 1;
        }
        return 0;
    }
}