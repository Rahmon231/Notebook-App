package com.lemzeeyyy.notebookapplication.utils

import androidx.room.TypeConverter
import java.sql.Timestamp

class TimeConverter {
        @TypeConverter
        fun toDate(timestamp: Long?): Timestamp? {
            return if (timestamp == null) null else Timestamp(timestamp)
        }

        @TypeConverter
        fun toTimestamp(time: Timestamp?): Long? {
            return time?.time
        }

}
