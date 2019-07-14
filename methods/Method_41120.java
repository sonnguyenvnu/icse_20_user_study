protected int storeExpressionVals(int pos,String s,int type) throws ParseException {
  int incr=0;
  int i=skipWhiteSpace(pos,s);
  if (i >= s.length()) {
    return i;
  }
  char c=s.charAt(i);
  if ((c >= 'A') && (c <= 'Z') && (!s.equals("L")) && (!s.equals("LW")) && (!s.matches("^L-[0-9]*[W]?"))) {
    String sub=s.substring(i,i + 3);
    int sval=-1;
    int eval=-1;
    if (type == MONTH) {
      sval=getMonthNumber(sub) + 1;
      if (sval <= 0) {
        throw new ParseException("Invalid Month value: '" + sub + "'",i);
      }
      if (s.length() > i + 3) {
        c=s.charAt(i + 3);
        if (c == '-') {
          i+=4;
          sub=s.substring(i,i + 3);
          eval=getMonthNumber(sub) + 1;
          if (eval <= 0) {
            throw new ParseException("Invalid Month value: '" + sub + "'",i);
          }
        }
      }
    }
 else     if (type == DAY_OF_WEEK) {
      sval=getDayOfWeekNumber(sub);
      if (sval < 0) {
        throw new ParseException("Invalid Day-of-Week value: '" + sub + "'",i);
      }
      if (s.length() > i + 3) {
        c=s.charAt(i + 3);
        if (c == '-') {
          i+=4;
          sub=s.substring(i,i + 3);
          eval=getDayOfWeekNumber(sub);
          if (eval < 0) {
            throw new ParseException("Invalid Day-of-Week value: '" + sub + "'",i);
          }
        }
 else         if (c == '#') {
          try {
            i+=4;
            nthdayOfWeek=Integer.parseInt(s.substring(i));
            if (nthdayOfWeek < 1 || nthdayOfWeek > 5) {
              throw new Exception();
            }
          }
 catch (          Exception e) {
            throw new ParseException("A numeric value between 1 and 5 must follow the '#' option",i);
          }
        }
 else         if (c == 'L') {
          lastdayOfWeek=true;
          i++;
        }
      }
    }
 else {
      throw new ParseException("Illegal characters for this position: '" + sub + "'",i);
    }
    if (eval != -1) {
      incr=1;
    }
    addToSet(sval,eval,incr,type);
    return (i + 3);
  }
  if (c == '?') {
    i++;
    if ((i + 1) < s.length() && (s.charAt(i) != ' ' && s.charAt(i + 1) != '\t')) {
      throw new ParseException("Illegal character after '?': " + s.charAt(i),i);
    }
    if (type != DAY_OF_WEEK && type != DAY_OF_MONTH) {
      throw new ParseException("'?' can only be specified for Day-of-Month or Day-of-Week.",i);
    }
    if (type == DAY_OF_WEEK && !lastdayOfMonth) {
      int val=daysOfMonth.last();
      if (val == NO_SPEC_INT) {
        throw new ParseException("'?' can only be specified for Day-of-Month -OR- Day-of-Week.",i);
      }
    }
    addToSet(NO_SPEC_INT,-1,0,type);
    return i;
  }
  if (c == '*' || c == '/') {
    if (c == '*' && (i + 1) >= s.length()) {
      addToSet(ALL_SPEC_INT,-1,incr,type);
      return i + 1;
    }
 else     if (c == '/' && ((i + 1) >= s.length() || s.charAt(i + 1) == ' ' || s.charAt(i + 1) == '\t')) {
      throw new ParseException("'/' must be followed by an integer.",i);
    }
 else     if (c == '*') {
      i++;
    }
    c=s.charAt(i);
    if (c == '/') {
      i++;
      if (i >= s.length()) {
        throw new ParseException("Unexpected end of string.",i);
      }
      incr=getNumericValue(s,i);
      i++;
      if (incr > 10) {
        i++;
      }
      checkIncrementRange(incr,type,i);
    }
 else {
      incr=1;
    }
    addToSet(ALL_SPEC_INT,-1,incr,type);
    return i;
  }
 else   if (c == 'L') {
    i++;
    if (type == DAY_OF_MONTH) {
      lastdayOfMonth=true;
    }
    if (type == DAY_OF_WEEK) {
      addToSet(7,7,0,type);
    }
    if (type == DAY_OF_MONTH && s.length() > i) {
      c=s.charAt(i);
      if (c == '-') {
        ValueSet vs=getValue(0,s,i + 1);
        lastdayOffset=vs.value;
        if (lastdayOffset > 30)         throw new ParseException("Offset from last day must be <= 30",i + 1);
        i=vs.pos;
      }
      if (s.length() > i) {
        c=s.charAt(i);
        if (c == 'W') {
          nearestWeekday=true;
          i++;
        }
      }
    }
    return i;
  }
 else   if (c >= '0' && c <= '9') {
    int val=Integer.parseInt(String.valueOf(c));
    i++;
    if (i >= s.length()) {
      addToSet(val,-1,-1,type);
    }
 else {
      c=s.charAt(i);
      if (c >= '0' && c <= '9') {
        ValueSet vs=getValue(val,s,i);
        val=vs.value;
        i=vs.pos;
      }
      i=checkNext(i,s,val,type);
      return i;
    }
  }
 else {
    throw new ParseException("Unexpected character: " + c,i);
  }
  return i;
}
