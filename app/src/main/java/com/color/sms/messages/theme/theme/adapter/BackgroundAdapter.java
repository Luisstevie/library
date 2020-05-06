package com.color.sms.messages.theme.theme.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.color.sms.messages.theme.R;
import com.color.sms.messages.theme.databinding.ListItemThemeBinding;
import com.color.sms.messages.theme.model.Theme;
import com.color.sms.messages.theme.theme.activity.PreviewThemeActivity;
import com.color.sms.messages.theme.utils.Constants;
import com.color.sms.messages.theme.utils.Glide4Engine;
import com.color.sms.messages.theme.utils.SharedPreferenceHelper;

import java.util.List;

public class BackgroundAdapter extends RecyclerView.Adapter<BackgroundAdapter.ViewHolder> {

    private Context context;
    private List<Theme> themes;
    private Glide4Engine glide4Engine;

    public BackgroundAdapter(Context context, List<Theme> themes) {
        this.context = context;
        this.themes = themes;
        glide4Engine = new Glide4Engine();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ListItemThemeBinding themeBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.list_item_theme, viewGroup, false);
        return new ViewHolder(themeBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(themes.get(position));
    }

    @Override
    public int getItemCount() {
        return themes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ListItemThemeBinding binding;

        public ViewHolder(@NonNull ListItemThemeBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;

            binding.background.setOnClickListener(v -> {
                Intent intent = new Intent(context, PreviewThemeActivity.class);
                intent.putExtra(Constants.BACKGROUND_THEME_COMPOSE, themes.get(getAdapterPosition()).getThemeCompose());
                intent.putExtra(Constants.BACKGROUND_THEME_MAIN, themes.get(getAdapterPosition()).getThemeMain());
                context.startActivity(intent);
            });
        }

        void bind(Theme theme) {
            glide4Engine.loadImage(binding.background, theme.getThemeCompose());
            if (SharedPreferenceHelper.getInstance(context).getInt(Constants.BACKGROUND_THEME_COMPOSE) == theme.getThemeCompose()){
                binding.selectedTheme.setVisibility(View.VISIBLE);
            }else {
                binding.selectedTheme.setVisibility(View.GONE);
            }
        }
    }
}
