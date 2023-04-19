package com.kortezz.backend.util;

import com.kortezz.backend.entity.Location;
import com.kortezz.backend.entity.Mail;

public class MailUtil {
    public static Mail generateMail(Location location) {
        Mail mail = new Mail();
        mail.setTo("mkaracan2002@yahoo.com");
        mail.setFrom("noreply@location.com");
        mail.setSubject("Location is added");
        mail.setText(String.format("Dear Admin, \n\n Location (city : %s, district: %s) ",
            location.getCity(), location.getDistrict()));
        return mail;
    }
}
