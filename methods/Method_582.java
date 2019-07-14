private boolean scanISO8601DateIfMatch(boolean strict,int rest){
  if (rest < 8) {
    return false;
  }
  char c0=charAt(bp);
  char c1=charAt(bp + 1);
  char c2=charAt(bp + 2);
  char c3=charAt(bp + 3);
  char c4=charAt(bp + 4);
  char c5=charAt(bp + 5);
  char c6=charAt(bp + 6);
  char c7=charAt(bp + 7);
  if ((!strict) && rest > 13) {
    char c_r0=charAt(bp + rest - 1);
    char c_r1=charAt(bp + rest - 2);
    if (c0 == '/' && c1 == 'D' && c2 == 'a' && c3 == 't' && c4 == 'e' && c5 == '(' && c_r0 == '/' && c_r1 == ')') {
      int plusIndex=-1;
      for (int i=6; i < rest; ++i) {
        char c=charAt(bp + i);
        if (c == '+') {
          plusIndex=i;
        }
 else         if (c < '0' || c > '9') {
          break;
        }
      }
      if (plusIndex == -1) {
        return false;
      }
      int offset=bp + 6;
      String numberText=this.subString(offset,bp + plusIndex - offset);
      long millis=Long.parseLong(numberText);
      calendar=Calendar.getInstance(timeZone,locale);
      calendar.setTimeInMillis(millis);
      token=JSONToken.LITERAL_ISO8601_DATE;
      return true;
    }
  }
  char c10;
  if (rest == 8 || rest == 14 || (rest == 16 && ((c10=charAt(bp + 10)) == 'T' || c10 == ' ')) || (rest == 17 && charAt(bp + 6) != '-')) {
    if (strict) {
      return false;
    }
    char y0, y1, y2, y3, M0, M1, d0, d1;
    char c8=charAt(bp + 8);
    final boolean c_47=c4 == '-' && c7 == '-';
    final boolean sperate16=c_47 && rest == 16;
    final boolean sperate17=c_47 && rest == 17;
    if (sperate17 || sperate16) {
      y0=c0;
      y1=c1;
      y2=c2;
      y3=c3;
      M0=c5;
      M1=c6;
      d0=c8;
      d1=charAt(bp + 9);
    }
 else     if (c4 == '-' && c6 == '-') {
      y0=c0;
      y1=c1;
      y2=c2;
      y3=c3;
      M0='0';
      M1=c5;
      d0='0';
      d1=c7;
    }
 else {
      y0=c0;
      y1=c1;
      y2=c2;
      y3=c3;
      M0=c4;
      M1=c5;
      d0=c6;
      d1=c7;
    }
    if (!checkDate(y0,y1,y2,y3,M0,M1,d0,d1)) {
      return false;
    }
    setCalendar(y0,y1,y2,y3,M0,M1,d0,d1);
    int hour, minute, seconds, millis;
    if (rest != 8) {
      char c9=charAt(bp + 9);
      c10=charAt(bp + 10);
      char c11=charAt(bp + 11);
      char c12=charAt(bp + 12);
      char c13=charAt(bp + 13);
      char h0, h1, m0, m1, s0, s1;
      if ((sperate17 && c10 == 'T' && c13 == ':' && charAt(bp + 16) == 'Z') || (sperate16 && (c10 == ' ' || c10 == 'T') && c13 == ':')) {
        h0=c11;
        h1=c12;
        m0=charAt(bp + 14);
        m1=charAt(bp + 15);
        s0='0';
        s1='0';
      }
 else {
        h0=c8;
        h1=c9;
        m0=c10;
        m1=c11;
        s0=c12;
        s1=c13;
      }
      if (!checkTime(h0,h1,m0,m1,s0,s1)) {
        return false;
      }
      if (rest == 17 && !sperate17) {
        char S0=charAt(bp + 14);
        char S1=charAt(bp + 15);
        char S2=charAt(bp + 16);
        if (S0 < '0' || S0 > '9') {
          return false;
        }
        if (S1 < '0' || S1 > '9') {
          return false;
        }
        if (S2 < '0' || S2 > '9') {
          return false;
        }
        millis=(S0 - '0') * 100 + (S1 - '0') * 10 + (S2 - '0');
      }
 else {
        millis=0;
      }
      hour=(h0 - '0') * 10 + (h1 - '0');
      minute=(m0 - '0') * 10 + (m1 - '0');
      seconds=(s0 - '0') * 10 + (s1 - '0');
    }
 else {
      hour=0;
      minute=0;
      seconds=0;
      millis=0;
    }
    calendar.set(Calendar.HOUR_OF_DAY,hour);
    calendar.set(Calendar.MINUTE,minute);
    calendar.set(Calendar.SECOND,seconds);
    calendar.set(Calendar.MILLISECOND,millis);
    token=JSONToken.LITERAL_ISO8601_DATE;
    return true;
  }
  if (rest < 9) {
    return false;
  }
  char c8=charAt(bp + 8);
  char c9=charAt(bp + 9);
  int date_len=10;
  char y0, y1, y2, y3, M0, M1, d0, d1;
  if ((c4 == '-' && c7 == '-') || (c4 == '/' && c7 == '/')) {
    y0=c0;
    y1=c1;
    y2=c2;
    y3=c3;
    M0=c5;
    M1=c6;
    d0=c8;
    d1=c9;
  }
 else   if ((c4 == '-' && c6 == '-')) {
    y0=c0;
    y1=c1;
    y2=c2;
    y3=c3;
    M0='0';
    M1=c5;
    if (c8 == ' ') {
      d0='0';
      d1=c7;
      date_len=8;
    }
 else {
      d0=c7;
      d1=c8;
      date_len=9;
    }
  }
 else   if ((c2 == '.' && c5 == '.') || (c2 == '-' && c5 == '-')) {
    d0=c0;
    d1=c1;
    M0=c3;
    M1=c4;
    y0=c6;
    y1=c7;
    y2=c8;
    y3=c9;
  }
 else   if (c8 == 'T') {
    y0=c0;
    y1=c1;
    y2=c2;
    y3=c3;
    M0=c4;
    M1=c5;
    d0=c6;
    d1=c7;
    date_len=8;
  }
 else {
    if (c4 == '?' || c4 == '?') {
      y0=c0;
      y1=c1;
      y2=c2;
      y3=c3;
      if (c7 == '?' || c7 == '?') {
        M0=c5;
        M1=c6;
        if (c9 == '?' || c9 == '?') {
          d0='0';
          d1=c8;
        }
 else         if (charAt(bp + 10) == '?' || charAt(bp + 10) == '?') {
          d0=c8;
          d1=c9;
          date_len=11;
        }
 else {
          return false;
        }
      }
 else       if (c6 == '?' || c6 == '?') {
        M0='0';
        M1=c5;
        if (c8 == '?' || c8 == '?') {
          d0='0';
          d1=c7;
        }
 else         if (c9 == '?' || c9 == '?') {
          d0=c7;
          d1=c8;
        }
 else {
          return false;
        }
      }
 else {
        return false;
      }
    }
 else {
      return false;
    }
  }
  if (!checkDate(y0,y1,y2,y3,M0,M1,d0,d1)) {
    return false;
  }
  setCalendar(y0,y1,y2,y3,M0,M1,d0,d1);
  char t=charAt(bp + date_len);
  if (t == 'T' && rest == 16 && date_len == 8 && charAt(bp + 15) == 'Z') {
    char h0=charAt(bp + date_len + 1);
    char h1=charAt(bp + date_len + 2);
    char m0=charAt(bp + date_len + 3);
    char m1=charAt(bp + date_len + 4);
    char s0=charAt(bp + date_len + 5);
    char s1=charAt(bp + date_len + 6);
    if (!checkTime(h0,h1,m0,m1,s0,s1)) {
      return false;
    }
    setTime(h0,h1,m0,m1,s0,s1);
    calendar.set(Calendar.MILLISECOND,0);
    if (calendar.getTimeZone().getRawOffset() != 0) {
      String[] timeZoneIDs=TimeZone.getAvailableIDs(0);
      if (timeZoneIDs.length > 0) {
        TimeZone timeZone=TimeZone.getTimeZone(timeZoneIDs[0]);
        calendar.setTimeZone(timeZone);
      }
    }
    token=JSONToken.LITERAL_ISO8601_DATE;
    return true;
  }
 else   if (t == 'T' || (t == ' ' && !strict)) {
    if (rest < date_len + 9) {
      return false;
    }
  }
 else   if (t == '"' || t == EOI || t == '?' || t == '?') {
    calendar.set(Calendar.HOUR_OF_DAY,0);
    calendar.set(Calendar.MINUTE,0);
    calendar.set(Calendar.SECOND,0);
    calendar.set(Calendar.MILLISECOND,0);
    ch=charAt(bp+=date_len);
    token=JSONToken.LITERAL_ISO8601_DATE;
    return true;
  }
 else   if (t == '+' || t == '-') {
    if (len == date_len + 6) {
      if (charAt(bp + date_len + 3) != ':' || charAt(bp + date_len + 4) != '0' || charAt(bp + date_len + 5) != '0') {
        return false;
      }
      setTime('0','0','0','0','0','0');
      calendar.set(Calendar.MILLISECOND,0);
      setTimeZone(t,charAt(bp + date_len + 1),charAt(bp + date_len + 2));
      return true;
    }
    return false;
  }
 else {
    return false;
  }
  if (charAt(bp + date_len + 3) != ':') {
    return false;
  }
  if (charAt(bp + date_len + 6) != ':') {
    return false;
  }
  char h0=charAt(bp + date_len + 1);
  char h1=charAt(bp + date_len + 2);
  char m0=charAt(bp + date_len + 4);
  char m1=charAt(bp + date_len + 5);
  char s0=charAt(bp + date_len + 7);
  char s1=charAt(bp + date_len + 8);
  if (!checkTime(h0,h1,m0,m1,s0,s1)) {
    return false;
  }
  setTime(h0,h1,m0,m1,s0,s1);
  char dot=charAt(bp + date_len + 9);
  int millisLen=-1;
  int millis=0;
  if (dot == '.') {
    if (rest < date_len + 11) {
      return false;
    }
    char S0=charAt(bp + date_len + 10);
    if (S0 < '0' || S0 > '9') {
      return false;
    }
    millis=S0 - '0';
    millisLen=1;
    if (rest > date_len + 11) {
      char S1=charAt(bp + date_len + 11);
      if (S1 >= '0' && S1 <= '9') {
        millis=millis * 10 + (S1 - '0');
        millisLen=2;
      }
    }
    if (millisLen == 2) {
      char S2=charAt(bp + date_len + 12);
      if (S2 >= '0' && S2 <= '9') {
        millis=millis * 10 + (S2 - '0');
        millisLen=3;
      }
    }
  }
  calendar.set(Calendar.MILLISECOND,millis);
  int timzeZoneLength=0;
  char timeZoneFlag=charAt(bp + date_len + 10 + millisLen);
  if (timeZoneFlag == ' ') {
    millisLen++;
    timeZoneFlag=charAt(bp + date_len + 10 + millisLen);
  }
  if (timeZoneFlag == '+' || timeZoneFlag == '-') {
    char t0=charAt(bp + date_len + 10 + millisLen + 1);
    if (t0 < '0' || t0 > '1') {
      return false;
    }
    char t1=charAt(bp + date_len + 10 + millisLen + 2);
    if (t1 < '0' || t1 > '9') {
      return false;
    }
    char t2=charAt(bp + date_len + 10 + millisLen + 3);
    char t3='0', t4='0';
    if (t2 == ':') {
      t3=charAt(bp + date_len + 10 + millisLen + 4);
      if (t3 != '0' && t3 != '3') {
        return false;
      }
      t4=charAt(bp + date_len + 10 + millisLen + 5);
      if (t4 != '0') {
        return false;
      }
      timzeZoneLength=6;
    }
 else     if (t2 == '0') {
      t3=charAt(bp + date_len + 10 + millisLen + 4);
      if (t3 != '0' && t3 != '3') {
        return false;
      }
      timzeZoneLength=5;
    }
 else     if (t2 == '3' && charAt(bp + date_len + 10 + millisLen + 4) == '0') {
      t3='3';
      t4='0';
      timzeZoneLength=5;
    }
 else     if (t2 == '4' && charAt(bp + date_len + 10 + millisLen + 4) == '5') {
      t3='4';
      t4='5';
      timzeZoneLength=5;
    }
 else {
      timzeZoneLength=3;
    }
    setTimeZone(timeZoneFlag,t0,t1,t3,t4);
  }
 else   if (timeZoneFlag == 'Z') {
    timzeZoneLength=1;
    if (calendar.getTimeZone().getRawOffset() != 0) {
      String[] timeZoneIDs=TimeZone.getAvailableIDs(0);
      if (timeZoneIDs.length > 0) {
        TimeZone timeZone=TimeZone.getTimeZone(timeZoneIDs[0]);
        calendar.setTimeZone(timeZone);
      }
    }
  }
  char end=charAt(bp + (date_len + 10 + millisLen + timzeZoneLength));
  if (end != EOI && end != '"') {
    return false;
  }
  ch=charAt(bp+=(date_len + 10 + millisLen + timzeZoneLength));
  token=JSONToken.LITERAL_ISO8601_DATE;
  return true;
}
