package it.jgiem.date;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import it.jgiem.date.dao.MemoDao;
import it.jgiem.date.entities.DateConverter;
import it.jgiem.date.entities.Memo;

@Database(entities = {Memo.class}, version = 2, exportSchema = false)
@TypeConverters({DateConverter.class})

public abstract class AppDatabase extends RoomDatabase {

    public abstract MemoDao memoDao();
    private static AppDatabase appDatabase;
    public synchronized static AppDatabase getInstance(Context context){
        if(appDatabase == null){
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, "my_memo_app").allowMainThreadQueries().build();
        } return appDatabase;
    }
}
