package com.example.sample;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sample.R;

import java.util.HashMap;
import java.util.Map;

public class DownloadActivity extends AppCompatActivity {

    TextView questionText;
    RadioGroup optionsGroup;
    Button nextButton;

    String currentLevel = "main";
    String selectedMain = "";
    String selectedSub = "";

    Map<String, String[]> data = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        questionText = findViewById(R.id.questionText);
        optionsGroup = findViewById(R.id.optionsGroup);
        nextButton = findViewById(R.id.nextButton);


        data.put("main", new String[]{"Sport", "سینما", "طبیعت"});

        data.put("Sport", new String[]{"Martial Art", "فوتبالی"});

        data.put("Martial Art", new String[]{"جودو", "بوکس", "کاراته"});

        data.put("Balls", new String[]{"فوتبال", "والیبال", "بسکتبال"});

        data.put("Cinema", new String[]{"فیلم ایرانی", "فیلم خارجی", "انیمیشن"});

        showOptions("main");

        nextButton.setOnClickListener(v -> {
            int selectedId = optionsGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(this, "لطفاً یک گزینه را انتخاب کنید", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedRadio = findViewById(selectedId);
            String choice = selectedRadio.getText().toString();

            if (currentLevel.equals("main")) {
                selectedMain = choice;
                if (data.containsKey(choice)) {
                    currentLevel = choice;
                    showOptions(choice);
                } else {
                    Toast.makeText(this, "پایان انتخاب‌ها", Toast.LENGTH_SHORT).show();
                }
            } else if (currentLevel.equals("ورزش")) {
                selectedSub = choice;
                currentLevel = choice;
                showOptions(choice);
            } else if (currentLevel.equals("رزمی") || currentLevel.equals("فوتبالی") || currentLevel.equals("سینما")) {

                handleFinalChoice(choice);
            } else {
                Toast.makeText(this, "پایان انتخاب‌ها", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showOptions(String key) {
        optionsGroup.removeAllViews();
        String[] options = data.get(key);
        if (options == null) {
            Toast.makeText(this, "هیچ گزینه‌ای وجود ندارد", Toast.LENGTH_SHORT).show();
            return;
        }

        for (String option : options) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(option);
            optionsGroup.addView(radioButton);
        }

        questionText.setText("گزینه مورد نظر را انتخاب کنید:");
    }

    private void handleFinalChoice(String choice) {
        Toast.makeText(this, "انتخاب نهایی: " + choice, Toast.LENGTH_SHORT).show();


        if (choice.equals("جودو")) {
            String imageUrl = "";
            downloadImage(imageUrl, "judo.jpg");
        } else if (choice.equals("بوکس")) {
            downloadImage("", "boxing.jpg");
        } else if (choice.equals("کاراته")) {
            downloadImage("", "karate.jpg");
        } else {
            Toast.makeText(this, "برای این گزینه تصویری تنظیم نشده", Toast.LENGTH_SHORT).show();
        }
    }

    private void downloadImage(String url, String fileName) {
        try {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            request.setTitle("در حال دانلود " + fileName);
            request.setDescription("دانلود تصویر...");
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);

            DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            manager.enqueue(request);

            Toast.makeText(this, "دانلود شروع شد!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "خطا در دانلود: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
