package de.example.anton.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import static de.example.anton.notes.R.string.app_name;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    EditText title;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        title = (EditText) findViewById(R.id.title);

        title.setHint("Title");

        Context context = getApplicationContext();
        this.sharedPref = context.getSharedPreferences(
                "MyPref", Context.MODE_PRIVATE);

        String notes;
        String titleText;

        if ((notes = this.sharedPref.getString("notes", null)) != null && (titleText = this.sharedPref.getString("title", null)) != null) {
            title.setText(titleText);
            editText.setText(notes);
        } else {
            Log.d("NotesAppNotes", "String cannot be found!");
        }





    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("NotesAppNotes", "Its paused mkay");

        Editor editor = sharedPref.edit();
        editor.putString("notes", editText.getText().toString());
        editor.putString("title", title.getText().toString());
        editor.commit();
    }


}
