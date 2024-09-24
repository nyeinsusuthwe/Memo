package it.jgiem.date.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import it.jgiem.date.entities.Memo;

@Dao
public interface MemoDao {
    @Insert
    void addMemo(Memo memo);

    @Update
    void updateMemo(Memo memo);

    @Delete
    void deleteMemo(Memo memo);

    @Query("SELECT * FROM memo")
    List<Memo> getAllMemos();
}
