package cueva.luis.trabajonotificaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.app.backup.SharedPreferencesBackupHelper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button preferenciasButton;
    private Button showPreferenciasButton;
    private Button notificacionButton;

    private static final String CHANNEL_ID = "21";
    private NotificationManager notificador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferenciasButton = findViewById(R.id.button);
        showPreferenciasButton = findViewById(R.id.button2);
        notificacionButton = findViewById(R.id.button3);

        createNotificationChannel();

        notificador = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        preferenciasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Preferencias.class);
                startActivity(i);
            }
        });

        showPreferenciasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                Log.i("Unico","Unico = " + pref.getBoolean("key-1-1", false));
                Log.i("Sistema","Sistema = " + pref.getString("key-2-1", "Windows"));
                Log.i("Version","Version = " + pref.getString("key-2-2", "0.0.0"));
            }
        });

        notificacionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                        .setSmallIcon(android.R.drawable.stat_sys_warning)
                        .setContentTitle("Ir a ejemplo Toast")
                        .setContentText("Vamos a aprobar la entrega")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setTicker("ENTREGA DE MULTIPLATAFORMA");

                Intent intent = new Intent(MainActivity.this, Notificacion.class);

                TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);
                stackBuilder.addParentStack(MainActivity.class);
                stackBuilder.addNextIntent(intent);

                try {
                    PendingIntent result = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_MUTABLE);
                    builder.setContentIntent(result);
                    builder.setAutoCancel(true);
                    notificador.notify(1, builder.build());
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        });

    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}