package com.andrody.play_media.plus;

/**
 * Created by Abboudi_Aliwi on 14.06.2017.
 * Website : http://andrody.com/
 * our channel on YouTube : https://www.youtube.com/c/Andrody2015
 * our page on Facebook : https://www.facebook.com/andrody2015/
 * our group on Facebook : https://www.facebook.com/groups/Programming.Android.apps/
 * our group on Whatsapp : https://chat.whatsapp.com/56JaImwTTMnCbQL6raHh7A
 * our group on Telegram : https://t.me/joinchat/AAAAAAm387zgezDhwkbuOA
 * Preview the app from Google play : https://play.google.com/store/apps/details?id=nasser_alqtami.andrody.com
 */

public class Data {
    String subject,link;

    public Data(String tt1, String tt2) {
        this.subject = tt1;
        this.link = tt2;
    }


    public String getSubject() {
        return this.subject;
    }

    public String getLink() {
        return this.link;
    }

}