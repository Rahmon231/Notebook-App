package com.lemzeeyyy.notebookapplication

import androidx.room.TypeConverter
import java.sql.Timestamp

class TimeConverter {

    companion object{
        @TypeConverter
        fun toDate(timestamp: Long?): Timestamp? {
            return if (timestamp == null) null else Timestamp(timestamp)
        }

        @TypeConverter
        fun toTimestamp(time: Timestamp?): Long? {
            return time?.time
        }
    }


}
