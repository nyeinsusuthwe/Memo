package it.jgiem.date.entities;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {
    @TypeConverter
    public Date longToDate(long timestamp){
        return new Date(timestamp);
    }

    @TypeConverter
    public long dateToLong(Date date){
        return date.getTime();
    }
}
