package it.marco.semantic.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UtilDate
{
    private final static Logger log = LoggerFactory.getLogger(UtilDate.class);
    public static String convertDate(long timestamp)
    {
        try
        {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timestamp);
            log.debug("Data is format.>>>>>> Original format: long timestamp.>>>>> Format: yyyy-MM-dd HH:mm:ss");
            return df.format(calendar.getTime());
        } catch (Exception e)
        {
            log.error("An exception was raised in changing the date");
            return "invalid date";
        }
    }
}
