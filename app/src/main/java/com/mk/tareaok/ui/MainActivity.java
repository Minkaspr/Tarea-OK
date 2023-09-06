package com.mk.tareaok.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.mk.tareaok.R;
import com.mk.tareaok.controller.ProfileController;
import com.mk.tareaok.model.Profile;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProfileController profileController;
    private List<Profile> profiles;
    private Spinner spinner;
    private LinearLayout enter;
    private Button addProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.profile_spinner);
        enter = findViewById(R.id.btnEnter);
        addProfile = findViewById(R.id.addProfile);

        profileController = new ProfileController();
        profiles = profileController.getProfiles();

//        if (profiles.isEmpty()) {
//            System.out.println("La lista de perfiles está vacía A");
//        } else {
//            for (Profile profile : profiles) {
//                System.out.println("A-----\t"+profile);
//            }
//        }

        enter.setOnClickListener(v -> {
            int selectedProfileIndex = spinner.getSelectedItemPosition();
            if (selectedProfileIndex >= 0) {
                Profile selectedProfile = profiles.get(selectedProfileIndex);
                int selectedProfileId = selectedProfile.getId_profile();
                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                intent.putExtra("profileId", selectedProfileId);
                startActivity(intent);
            }
        });

        addProfile.setOnClickListener(view -> {
            // Se crea una instancia de AddProfileBsdFragment y pasa el ProfileController.
            AddProfileBsdFragment addProfileBsdFragment = new AddProfileBsdFragment(profileController);
            // Se configura un listener para ser notificado cuando el fragmento se cierre o descarte.
            addProfileBsdFragment.setOnDismissListener(this::updateSpinnerAdapter);
            // Se muestra el fragmento utilizando el FragmentManager.
            addProfileBsdFragment.show(getSupportFragmentManager(), "AddProfileBsdFragment");
        });
    }

    private void updateSpinnerAdapter() {
        profiles = profileController.getProfiles();
//        if (profiles.isEmpty()) {
//            System.out.println("La lista de perfiles está vacía B");
//        } else {
//            for (Profile profile : profiles) {
//                System.out.println("B-----\t"+profile);
//            }
//        }
        // Se crea un ArrayAdapter para vincular la lista de perfiles (profiles) con el Spinner.
        ArrayAdapter<Profile> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, profiles);
        spinner.setAdapter(adapter);
    }
}