package com.mk.tareaok.controller;

import com.mk.tareaok.model.Profile;
import java.util.ArrayList;
import java.util.List;

public class ProfileController {
    private int nextId = 1;
    private List<Profile> profiles;

    public ProfileController() {
        profiles = new ArrayList<>();
    }
    public void createProfile(String name) {
        int id = nextId++;
        Profile profile = new Profile();
        profile.setId_profile(id);
        profile.setName(name);
        System.out.println("\tNuevo Perfil: "+id+"- "+name);
        profiles.add(profile);

        for (Profile p : profiles) {
            System.out.println("Perfil: "+p.getId_profile()+" "+p.getName());
        }
    }

    public List<Profile> getProfiles() {
        List<Profile> lista = new ArrayList<>();

        lista.addAll(profiles);

        /*if (profiles.isEmpty()) {
            System.out.println("No existe perfiles");
        } else {
            for (Profile profile : profiles) {
                System.out.println("Perfil: "+profile.getId_profile()+" "+profile.getName());
            }
        }*/
        return lista;
    }

    public Profile getProfileById(int profileId) {
        List<Profile> profiles = getProfiles();
        for (Profile profile : profiles) {
            if (profile.getId_profile() == profileId) {
                return profile;
            }
        }
        return null;
    }
}
