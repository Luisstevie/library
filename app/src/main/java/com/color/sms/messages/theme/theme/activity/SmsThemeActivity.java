package com.color.sms.messages.theme.theme.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.color.sms.messages.theme.MainActivity;
import com.color.sms.messages.theme.R;
import com.color.sms.messages.theme.base.BaseActivity;
import com.color.sms.messages.theme.databinding.ActivitySmsThemeBinding;
import com.color.sms.messages.theme.model.Theme;
import com.color.sms.messages.theme.theme.adapter.BackgroundAdapter;
import com.color.sms.messages.theme.utils.Constants;
import com.color.sms.messages.theme.utils.SharedPreferenceHelper;

import java.util.ArrayList;
import java.util.List;

public class SmsThemeActivity extends BaseActivity {

    private ActivitySmsThemeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_theme);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sms_theme);
        binding.setActivity(this);

        if (SharedPreferenceHelper.getInstance(this).getInt(Constants.BACKGROUND_THEME_COMPOSE) != 0 ||
                SharedPreferenceHelper.getInstance(this).getInt(Constants.BACKGROUND_THEME_MAIN) != 0) {
            binding.btnDefaultTheme.setVisibility(View.VISIBLE);
        } else {
            binding.btnDefaultTheme.setVisibility(View.GONE);
        }
        addData();
        onClick();
    }

    private void onClick() {
        binding.btnDefaultTheme.setOnClickListener(v -> {
            SharedPreferenceHelper.getInstance(SmsThemeActivity.this).setInt(Constants.BACKGROUND_THEME_MAIN, 0);
            SharedPreferenceHelper.getInstance(SmsThemeActivity.this).setInt(Constants.BACKGROUND_THEME_COMPOSE, 0);
            Intent intent = new Intent(SmsThemeActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    private void addData() {
        List<Theme> themes = new ArrayList<>();
        themes.add(new Theme(R.drawable.theme_main_1, R.drawable.theme_compose_1));
        themes.add(new Theme(R.drawable.theme_main_2, R.drawable.theme_compose_2));
        themes.add(new Theme(R.drawable.theme_main_3, R.drawable.theme_compose_3));
        themes.add(new Theme(R.drawable.theme_main_4, R.drawable.theme_compose_4));
        themes.add(new Theme(R.drawable.theme_main_5, R.drawable.theme_compose_5));

        BackgroundAdapter backgroundAdapter = new BackgroundAdapter(this, themes);
        binding.recyclerBackground.setLayoutManager(new GridLayoutManager(this, 2));
        binding.recyclerBackground.setAdapter(backgroundAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
