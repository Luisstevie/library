package com.color.sms.messages.theme.theme.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.color.sms.messages.theme.MainActivity;
import com.color.sms.messages.theme.R;
import com.color.sms.messages.theme.base.BaseActivity;
import com.color.sms.messages.theme.databinding.ActivityPreviewThemeBinding;
import com.color.sms.messages.theme.utils.Constants;
import com.color.sms.messages.theme.utils.Glide4Engine;
import com.color.sms.messages.theme.utils.SharedPreferenceHelper;

public class PreviewThemeActivity extends BaseActivity {

    private ActivityPreviewThemeBinding binding;
    private int themeMain, themeCompose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_theme);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_preview_theme);
        binding.setActivity(this);

        getDataIntent();
        initView();
        onClick();
    }

    private void onClick() {
        binding.btnApply.setOnClickListener(v -> {
            SharedPreferenceHelper.getInstance(PreviewThemeActivity.this).setInt(Constants.BACKGROUND_THEME_MAIN, themeMain);
            SharedPreferenceHelper.getInstance(PreviewThemeActivity.this).setInt(Constants.BACKGROUND_THEME_COMPOSE, themeCompose);
            Intent intent = new Intent(PreviewThemeActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    private void initView() {
        Glide4Engine glide4Engine = new Glide4Engine();
        glide4Engine.loadImage(binding.imagePreview, themeCompose);
    }

    private void getDataIntent() {
        themeMain = getIntent().getIntExtra(Constants.BACKGROUND_THEME_MAIN, 0);
        themeCompose = getIntent().getIntExtra(Constants.BACKGROUND_THEME_COMPOSE, 0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
