package com.rafsanjani.sandstorm.utility;

import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Slf4j
public class Generator {

    public static String generatePrimaryKey(String id)  {

        return Hashing.murmur3_32_fixed().hashString(id, StandardCharsets.UTF_8).toString();
    }

    public static String formatInstant(Instant instant){

        Date date = Date.from(instant);
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM, yyyy HH:mm:ss:mm");

        return formatter.format(date);
    }
}
