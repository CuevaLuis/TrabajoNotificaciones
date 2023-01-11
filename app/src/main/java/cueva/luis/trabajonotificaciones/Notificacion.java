package cueva.luis.trabajonotificaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class Notificacion extends AppCompatActivity {

    private Button buttonToast;
    private EditText editTextName;
    private SeekBar seekBarGravity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacion);

        buttonToast = findViewById(R.id.button4);
        editTextName = findViewById(R.id.editTextTextPersonName);
        seekBarGravity = findViewById(R.id.seekBar);

        buttonToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), editTextName.getText(), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER|Gravity.LEFT, 0, seekBarGravity.getThumbOffset());
                toast.show();
            }
        });
    }

}