package com.rafsanjani.sandstorm.utility;

import org.apache.commons.codec.digest.DigestUtils;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.logging.Logger;

public class Generator {

    private static Logger logger = Logger.getLogger(Generator.class.getName());

    public static String generatePrimaryKey(String id)  {

        return DigestUtils.sha1Hex(id);
    }

    public static String formatInstant(Instant instant){

        Date date = Date.from(instant);
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM, yyyy HH:mm:ss:mm");

        return formatter.format(date);
    }
}
