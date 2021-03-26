package edu.byu.cs240.familymapclient.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import edu.byu.cs240.familymapclient.R;
import edu.byu.cs240.familymapclient.model.DataCache;
import requests.LoginRequest;

public class MapFragment extends Fragment {
    private Button logoutButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        logoutButton = (Button) view.findViewById(R.id.btLogout);
        logoutButton.setOnClickListener(v -> {
            logOut();
            renderLoginFragment();
        });
        return view;
    }

    private void renderLoginFragment() {
        FragmentManager fm = this.getParentFragmentManager();
        LoginFragment loginFragment = new LoginFragment();

        fm.beginTransaction().replace(R.id.mainActivityFrameLayout, loginFragment).commit();
    }

    private void logOut() {
        DataCache.clear();
    }
}