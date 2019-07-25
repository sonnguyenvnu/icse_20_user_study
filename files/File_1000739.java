package org.nutz.mvc.adaptor.convertor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.nutz.castor.Castors;
import org.nutz.lang.Strings;
import org.nutz.lang.Times;
import org.nutz.mvc.adaptor.ParamConvertor;

public class DateParamConvertor implements ParamConvertor {

    private Class<?> type;
    private DateFormat dfmt;

    public DateParamConvertor(Class<?> type, String datefmt, String locale) {
        this.type = type;
        if (Strings.isBlank(datefmt)) {
            dfmt = null;
        } else {
            if (Strings.isBlank(locale))
                dfmt = new SimpleDateFormat(datefmt);
            else
                dfmt = new SimpleDateFormat(datefmt, new Locale(locale));
        }
    }

    public Object convert(String[] ss) {
        if (null == ss || ss.length == 0)
            return null;

        if (Strings.isBlank(ss[0]))
            return null;

        // å¦‚æžœä¸?ä¸º nullï¼Œå¿…ç„¶è¦?è½¬æ?¢æˆ?æ—¥æœŸ
        if (null != dfmt) {
            Date o = Times.parseq(dfmt, ss[0]);
            return Castors.me().castTo(o, type);
        }
        // é»˜è®¤é‡‡ç”¨è½¬æ?¢å™¨è½¬æ?¢
        return Castors.me().cast(ss[0], String.class, type);
    }
}
