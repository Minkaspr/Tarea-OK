package com.mk.tareaok.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.mk.tareaok.R;
import com.mk.tareaok.controller.ProfileController;

public class AddProfileBsdFragment extends BottomSheetDialogFragment {

    private EditText txtName;
    private ImageView closeButton;
    private Button addProfileButton;
    private ProfileController profileController;
    private OnDismissListener onDismissListener;

    public AddProfileBsdFragment(ProfileController profileController) {
        this.profileController = profileController;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.add_profile_bsd_fragment,container,false);

        closeButton = view.findViewById(R.id.closeBsdFragment);
        addProfileButton = view.findViewById(R.id.btnAddProfile);
        txtName = view.findViewById(R.id.txtName);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        closeButton.setOnClickListener(v -> dismiss());
        addProfileButton.setOnClickListener(v -> addProfile());

    }

    // Notifica que el fragmento se cerró o descartó, si se configuró un listener.
    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    // Interfaz para notificar el cierre o descarte del fragmento.
    public interface OnDismissListener {
        // Método llamado cuando el fragmento se cierra o descarta.
        void onDismiss();
    }

    // Configura el listener para el cierre o descarte del fragmento.
    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    private void addProfile() {
        String name = txtName.getText().toString();
        profileController.createProfile(name);
        System.out.println("----> \t"+name);
        dismiss();
    }
}

