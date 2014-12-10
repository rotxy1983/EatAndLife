package eat.life.rosario.bean;

import android.net.Uri;

import java.util.Date;

/**
 * Created by Rosario on 09/12/2014.
 */
public class Image {



    private int id;

    private Date date;
    private Uri imageUri;

    public Image(){

    }

    public Date getDate() {
        return date;
    }

    public Image(int id, Date date, Uri uri){
        this.id = id;
        this.date = date;
        this.imageUri = uri;
    }

    public int getId() {
        return id;
    }


    public Uri getImageUri() {
        return imageUri;
    }

}

