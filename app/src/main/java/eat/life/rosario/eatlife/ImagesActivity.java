package eat.life.rosario.eatlife;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class ImagesActivity extends Activity {

    private static final int SELECT_PICTURE1 = 1;
    private static final int SELECT_PICTURE2 = 2;

    private String selectedImagePath;
    private ImageView img;

    private String selectedImagePath2;
    private ImageView img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        img = (ImageView)findViewById(R.id.myImage1);

        ((Button) findViewById(R.id.compareImages1)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE1);
            }
        });

        img2 = (ImageView)findViewById(R.id.myImage2);

        ((Button) findViewById(R.id.compareImages2)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE2);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_images, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("Data componente is " + data.getAction() );
        //data.getAction()

        if (resultCode == RESULT_OK  ) {
            if (requestCode == SELECT_PICTURE1) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                System.out.println("Image Path for Pict 1 is : " + selectedImagePath);
                System.out.println("Image Path for Pict 2 is  : " + selectedImagePath2);
                img.setImageURI(selectedImageUri);
            }
            if (requestCode == SELECT_PICTURE2) {
                Uri selectedImageUri2 = data.getData();
                selectedImagePath2 = getPath(selectedImageUri2);
                System.out.println("Image Path for Pict 2 is  : " + selectedImagePath2);
                System.out.println("Image Path for Pict 1 is : " + selectedImagePath);
                img2.setImageURI(selectedImageUri2);
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}