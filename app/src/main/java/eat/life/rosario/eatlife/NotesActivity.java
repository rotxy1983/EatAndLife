package eat.life.rosario.eatlife;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;
import java.util.List;

import eat.life.rosario.DBManager.NotesDBHandler;
import eat.life.rosario.bean.Note;


public class NotesActivity extends Activity {

    NotesDBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        dbHandler = new NotesDBHandler( getApplicationContext(),null, null, 0);
        final Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteToSave = ((EditText)findViewById(R.id.editText)).getText().toString();
                System.out.println("LA NOTA A SALVAR DICE " + noteToSave );
                System.out.println("LA FECHA A SALVAR DICE " + new Date());
                Note note = new Note( dbHandler.getNotesCount(),noteToSave,new Date());
                dbHandler.createNote(note);
                List<Note> notes = dbHandler.getAllNotes();
                for(Note n:notes){
                    System.out.println("!!!LA NOTA QUE SE GUARDO DICE " + n.getNote());
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notes, menu);
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
}
