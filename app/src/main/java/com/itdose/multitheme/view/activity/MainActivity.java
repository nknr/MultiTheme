package com.itdose.multitheme.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.itdose.multitheme.view.fragment.PreferencesFragment;
import com.itdose.multitheme.R;
import com.itdose.multitheme.view.fragment.SettingsFragment;
import com.itdose.multitheme.view.fragment.WelcomeFragment;
import com.itdose.multitheme.utils.ColorUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));
        showFragment(SettingsFragment.TAG);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        // This demonstrates how to programmatically tint a drawable
        MenuItem item = menu.findItem(R.id.action_more);
        Drawable drawableWrap = DrawableCompat.wrap(item.getIcon()).mutate();
        DrawableCompat.setTint(drawableWrap, ColorUtils.getThemeColor(this, R.attr.colorOnPrimary));
        item.setIcon(drawableWrap);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_more) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showFragment(@NonNull String tag) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (fragment == null) {
            switch (tag) {
                case PreferencesFragment.TAG: {
                    fragment = new PreferencesFragment();
                    break;
                }
                case SettingsFragment.TAG: {
                    fragment = new SettingsFragment();
                    break;
                }
                default: {
                    fragment = new WelcomeFragment();
                    break;
                }
            }
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_layout, fragment, tag)
                .commit();
    }
}
