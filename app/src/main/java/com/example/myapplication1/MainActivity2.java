package com.example.myapplication1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        PDFView pdfView = findViewById(R.id.cse11);

        String dept = getIntent().getStringExtra("dept");
        String level = getIntent().getStringExtra("level_term");

        String to_search = dept + "_" + level + ".pdf";

        pdfView.fromAsset(to_search).load();
    }
}