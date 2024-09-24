package it.jgiem.date;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import it.jgiem.date.adapters.MemoAdapter;
import it.jgiem.date.dao.MemoDao;
import it.jgiem.date.databinding.ActivityMainBinding;
import it.jgiem.date.entities.Memo;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MemoDao memoDao;
    private List<Memo> memo;
    private MemoAdapter memoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initListeners();
        initDatabase();
        initUi();
    }



    @Override
    protected void onStart() {
        super.onStart();
        refreshNotes();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 123 && resultCode == RESULT_OK){
            Memo memo = (Memo) data.getSerializableExtra("memo");
            memoDao.addMemo(memo);
            refreshNotes();
        }
    }

    private void initUi() {
        memoAdapter = new MemoAdapter(memo, memo ->{
            Intent intent = new Intent(this, EditActivity.class);
            intent.putExtra("memo", memo);
            startActivity(intent);
        });
        binding.rv.setAdapter(memoAdapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
    }



    private void initDatabase() {
        var db = AppDatabase.getInstance(this);
        memoDao = db.memoDao();
        //memoDao.addMemo(new Memo("Anniversary", "01/01/2024", "09408190011" ));
        memo = memoDao.getAllMemos();
    }



    private void initListeners() {
        binding.fabAdd.setOnClickListener(view ->{
            Intent intent = new Intent(this, EditActivity.class);
            startActivityForResult(intent, 123);
        });
    }



    private void refreshNotes(){
        memo = memoDao.getAllMemos();
        memoAdapter.setMemos(memo);
    }
}