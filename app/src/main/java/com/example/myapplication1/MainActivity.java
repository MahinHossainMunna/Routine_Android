package com.example.myapplication1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String selectedDepartment, selectedLevel;
    private TextView tvDepartmentSpinner, tvLevelSpinner;
    private Spinner departmentSpinner, levelSpinner;
    public ArrayAdapter<CharSequence> departmentAdapter, levelAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Department Spinner Initialisation
        departmentSpinner = findViewById(R.id.spinner_departments);

        //ArrayAdapter for using string array we will define
        departmentAdapter = ArrayAdapter.createFromResource(this, R.array.array_departments, R.layout.spinner_layout);

        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        departmentSpinner.setAdapter(departmentAdapter);

        departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                levelSpinner = findViewById(R.id.spinner_level);

                selectedDepartment = departmentSpinner.getSelectedItem().toString();

                int parentID = parent.getId();
                if (parentID == R.id.spinner_departments) {
                    switch (selectedDepartment) {
                        case "Select Your Department":
                            levelAdapter = ArrayAdapter.createFromResource(parent.getContext(), R.array.array_default_level, R.layout.spinner_layout);
                            break;
                        case "CSE":
                            levelAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_CSE, R.layout.spinner_layout);
                            break;

                        case "EEE":
                            levelAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_EEE, R.layout.spinner_layout);
                            break;

                        case "CE":
                            levelAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_CE, R.layout.spinner_layout);
                            break;

                        case "BBA":
                            levelAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_DBA, R.layout.spinner_layout);
                            break;

                        case "Eng":
                            levelAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_ENG, R.layout.spinner_layout);
                            break;

                        case "Law":
                            levelAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                    R.array.array_LAW, R.layout.spinner_layout);
                            break;

                        default:
                            break;
                    }
                    levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    levelSpinner.setAdapter(levelAdapter);

                    levelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            selectedLevel = levelSpinner.getSelectedItem().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Button submitButton = findViewById(R.id.button_submit);
        tvDepartmentSpinner = findViewById(R.id.textView_department);
        tvLevelSpinner = findViewById(R.id.textView_level);

        submitButton.setOnClickListener(v -> {
            if (selectedDepartment.equals("Select Your Department")) {
                Toast.makeText(MainActivity.this, "Please Select Your Department", Toast.LENGTH_LONG).show();
                tvDepartmentSpinner.setError("Department is Required!");
                tvDepartmentSpinner.requestFocus();
            } else if (selectedLevel.equals("Select Your Level-Term")) {
                Toast.makeText(MainActivity.this, "Please Select Your Level & Term", Toast.LENGTH_LONG).show();
                tvLevelSpinner.setError("Level-Term is Required!");
                tvLevelSpinner.requestFocus();
                tvDepartmentSpinner.setError(null);
            } else {
                tvDepartmentSpinner.setError(null);
                tvLevelSpinner.setError(null);

                String lev = "";

                switch (selectedLevel) {
                    case "Level-1 Term-I":
                        lev = "1_1";
                        break;
                    case "Level-1 Term-II":
                        lev = "1_2";
                        break;
                    case "Level-2 Term-I":
                        lev = "2_1";
                        break;
                    case "Level-2 Term-II":
                        lev = "2_2";
                        break;
                    case "Level-3 Term-I":
                        lev = "3_1";
                        break;
                    case "Level-3 Term-II":
                        lev = "3_2";
                        break;
                    case "Level-4 Term-I":
                        lev = "4_1";
                        break;
                    case "Level-4 Term-II":
                        lev = "4_2";
                        break;
                    default: break;
                }

                //String follow = selectedDepartment + lev + ".pdf";

                //Toast.makeText(MainActivity.this, "Selected Department: "+selectedDepartment +"\nSelected Level-Term: "+selectedLevel, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("dept", selectedDepartment);
                intent.putExtra("level_term", lev);
                startActivity(intent);
            }
        });
    }
}
