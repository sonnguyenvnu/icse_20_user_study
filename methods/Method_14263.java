private Object getPart(Calendar c,String part){
  if ("hours".equals(part) || "hour".equals(part) || "h".equals(part)) {
    return c.get(Calendar.HOUR_OF_DAY);
  }
 else   if ("minutes".equals(part) || "minute".equals(part) || "min".equals(part)) {
    return c.get(Calendar.MINUTE);
  }
 else   if ("seconds".equals(part) || "sec".equals(part) || "s".equals(part)) {
    return c.get(Calendar.SECOND);
  }
 else   if ("milliseconds".equals(part) || "ms".equals(part) || "S".equals(part)) {
    return c.get(Calendar.MILLISECOND);
  }
 else   if ("years".equals(part) || "year".equals(part)) {
    return c.get(Calendar.YEAR);
  }
 else   if ("months".equals(part) || "month".equals(part)) {
    return c.get(Calendar.MONTH) + 1;
  }
 else   if ("weeks".equals(part) || "week".equals(part) || "w".equals(part)) {
    return c.get(Calendar.WEEK_OF_MONTH);
  }
 else   if ("days".equals(part) || "day".equals(part) || "d".equals(part)) {
    return c.get(Calendar.DAY_OF_MONTH);
  }
 else   if ("weekday".equals(part)) {
    return s_daysOfWeek[c.get(Calendar.DAY_OF_WEEK)];
  }
 else   if ("time".equals(part)) {
    return c.getTimeInMillis();
  }
 else {
    return new EvalError("Date unit '" + part + "' not recognized.");
  }
}
