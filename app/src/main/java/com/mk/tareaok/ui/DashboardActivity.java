package com.mk.tareaok.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.mk.tareaok.R;
import com.mk.tareaok.controller.ProfileController;
import com.mk.tareaok.model.Profile;

public class DashboardActivity extends AppCompatActivity {

    private TextView name;
    private ProfileController profileController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        name = findViewById(R.id.titleName);
        //---------↓¦↓¦↓¦↓¦↓¦↓¦↓¦↓¦↓¦↓¦↓¦↓¦↓¦↓¦↓¦↓
        profileController = new ProfileController();

        int selectedProfileId = getIntent().getIntExtra("profileId", -1);
        //name.setText("ID del perfil seleccionado: " + selectedProfileId);

        if (selectedProfileId != -1) {
            Profile selectedProfile = profileController.getProfileById(selectedProfileId);
            System.out.println("ID del perfil seleccionado: " + selectedProfileId);

            if (selectedProfile != null) {
                name.setText(selectedProfile.getName());
            } else {
                name.setText("Perfil no encontrado");
            }
        } else {
            name.setText("ID de perfil no existe");
        }
    }
}