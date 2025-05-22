package com.example.savename;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private Button buttonSave;
    private TextView textViewWelcome;
    private SharedPreferences sharedPreferences;

    // Constants for SharedPreferences
    private static final String PREFS_NAME = "UserInfoPrefs";
    private static final String KEY_NAME = "UserName";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Views
        editTextName = findViewById(R.id.editTextName);
        buttonSave = findViewById(R.id.buttonSave);
        textViewWelcome = findViewById(R.id.textViewWelcome);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Load saved name on startup
        loadSavedName();

        // Set listener for the Save button
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveName();
            }
        });
    }

    private void loadSavedName() {
        String savedName = sharedPreferences.getString(KEY_NAME, null); // Get saved name, default is null

        if (savedName != null && !savedName.isEmpty()) {
            textViewWelcome.setText("Welcome back, " + savedName + "!");
            // Optionally pre-fill EditText if you want
            // editTextName.setText(savedName);
        } else {
            textViewWelcome.setText("Welcome!"); // Default message
        }
    }

    private void saveName() {
        String nameToSave = editTextName.getText().toString().trim(); // Get text from EditText

        if (!nameToSave.isEmpty()) {
            SharedPreferences.Editor editor = sharedPreferences.edit(); // Get editor
            editor.putString(KEY_NAME, nameToSave); // Put the name string
            editor.apply(); // Apply changes asynchronously (preferred)

            // Update welcome message immediately
            textViewWelcome.setText("Welcome back, " + nameToSave + "!");
            Toast.makeText(this, "Name saved!", Toast.LENGTH_SHORT).show();
            // Clear the EditText after saving (optional)
            // editTextName.setText("");
        } else {
            Toast.makeText(this, "Please enter a name.", Toast.LENGTH_SHORT).show();
        }
    }
}