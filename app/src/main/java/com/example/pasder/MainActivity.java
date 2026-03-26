package com.example.pasder;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi BottomNavigationView sesuai ID di XML
        bottomNavigationView = findViewById(R.id.bottom_nav);

        // Menampilkan HomeFragment sebagai halaman awal saat aplikasi dibuka
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }

        // Listener untuk menangani klik pada menu navigasi bawah
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (id == R.id.nav_assignment) {
                selectedFragment = new AssignmentFragment();
            } else if (id == R.id.nav_saya) {
                selectedFragment = new SayaFragment();
            }

            if (selectedFragment != null) {
                loadFragment(selectedFragment);
            }
            return true;
        });
    }

    /**
     * Fungsi pembantu untuk mengganti fragment di dalam fragment_container.
     */
    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    /**
     * Fungsi untuk update status sewa dan pindah navigasi secara programmatically.
     */
    public void updateNavigasi(boolean isSewa) {
        getSharedPreferences("PasDerPrefs", MODE_PRIVATE)
                .edit().putBoolean("is_sewa_active", isSewa).apply();

        // Menggunakan ID menu yang benar: nav_home
        if (bottomNavigationView != null) {
            bottomNavigationView.setSelectedItemId(R.id.nav_home);
        }
    }
}
