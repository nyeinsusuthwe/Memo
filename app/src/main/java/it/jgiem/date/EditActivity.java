package it.jgiem.date;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import it.jgiem.date.dao.MemoDao;
import it.jgiem.date.databinding.ActivityEditBinding;
import it.jgiem.date.entities.Memo;

public class EditActivity extends AppCompatActivity {

    private ActivityEditBinding binding;
    private Memo memo;
    private MemoDao memoDao;
    private DatePickerDialog datePickerDialog;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUi();
        initListeners();
        initDatabase();
        initDatePickerDialog();
    }

    private void initDatePickerDialog() {
        datePickerDialog = new DatePickerDialog(this);
        datePickerDialog.setOnDateSetListener((view, year, month, dayofMonth) ->{
            var selectedDate = LocalDate.of(year, month+1, dayofMonth);
            binding.btDate.setText(formatter.format(selectedDate));
        });
    }

    private void initDatabase() {
        memoDao = AppDatabase.getInstance(this).memoDao();
    }

    private void initListeners() {
        binding.btCancle.setOnClickListener(view ->{
            if(memo == null){  //ADD NOTE - Cancle
                finish();
            } else {
                memoDao.deleteMemo(memo);  //UPDATE NOTE - Delete
                finish();
            }
        });


        binding.btDate.setOnClickListener(view -> {
            datePickerDialog.show();
        });



        binding.btSave.setOnClickListener(view ->{
            String name = binding.etImportance.getText().toString();
            String contact = binding.etContact.getText().toString();
            String date = binding.btDate.getText().toString();

            if(memo == null){   //ADD Memo - add
                if(!name.isBlank() && !contact.isBlank() && !date.isBlank()){
                    Memo memo = new Memo(name, date, contact);
                    Intent intent = new Intent();
                    intent.putExtra("memo", memo);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            } else {   //UPDATE NOTE - Update
                if(!name.isBlank() && !contact.isBlank() && !date.isBlank()){
                    if(!name.equals(memo.getName()) || !contact.equals(memo.getContact()) || !date.equals(memo.getDate())){
                        memo.setName(name);
                        memo.setContact(contact);
                        memo.setDate(date);
                        memoDao.updateMemo(memo);
                        finish();
                    }
                } else {
                    Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initUi() {
        if(getIntent() != null){
            memo = (Memo) getIntent().getSerializableExtra("memo");

            if(memo != null){
                binding.etImportance.setText(memo.getName());
                binding.etContact.setText(memo.getContact());
                binding.btDate.setText(memo.getDate());
                binding.btSave.setText("Update");
                binding.btCancle.setText("Delete");
            }
        }
    }

}