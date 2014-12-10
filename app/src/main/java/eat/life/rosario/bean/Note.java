package eat.life.rosario.bean;

import android.net.Uri;
import java.util.Date;

/**
 * Created by Rosario on 08/12/2014.
 */
public class Note {


    private String note;
    private int id;
    private Date date;

    public Note(){

    }

    public Note(int id, String note, Date date){
        this.id = id;
        this.note = note;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public Date getDate() {
        return date;
    }


    /*public Uri getImageUri() {
        return imageUri;
    }*/

}
