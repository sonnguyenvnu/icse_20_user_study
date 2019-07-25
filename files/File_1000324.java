package org.nutz.castor.castor;

import org.nutz.lang.Strings;

public class String2Datetime extends DateTimeCastor<String, java.util.Date> {

    @Override
    public java.util.Date cast(String src, Class<?> toType, String... args) {
        // å¤„ç?†ç©ºç™½
        if (Strings.isBlank(src))
            return null;
        return toDate(src);
    }

}
