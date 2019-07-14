private Object getPart(OffsetDateTime offsetDateTime,String part){
  if ("hours".equals(part) || "hour".equals(part) || "h".equals(part)) {
    return offsetDateTime.getHour();
  }
 else   if ("minutes".equals(part) || "minute".equals(part) || "min".equals(part)) {
    return offsetDateTime.getMinute();
  }
 else   if ("seconds".equals(part) || "sec".equals(part) || "s".equals(part)) {
    return offsetDateTime.getSecond();
  }
 else   if ("milliseconds".equals(part) || "ms".equals(part) || "S".equals(part)) {
    return Math.round(offsetDateTime.getNano() / 1000);
  }
 else   if ("nanos".equals(part) || "nano".equals(part) || "n".equals(part)) {
    return offsetDateTime.getNano();
  }
 else   if ("years".equals(part) || "year".equals(part)) {
    return offsetDateTime.getYear();
  }
 else   if ("months".equals(part) || "month".equals(part)) {
    return offsetDateTime.getMonth().getValue();
  }
 else   if ("weeks".equals(part) || "week".equals(part) || "w".equals(part)) {
    return getWeekOfMonth(offsetDateTime);
  }
 else   if ("days".equals(part) || "day".equals(part) || "d".equals(part)) {
    return offsetDateTime.getDayOfMonth();
  }
 else   if ("weekday".equals(part)) {
    return offsetDateTime.getDayOfWeek().name();
  }
 else   if ("time".equals(part)) {
    return offsetDateTime.toInstant().toEpochMilli();
  }
 else {
    return new EvalError("Date unit '" + part + "' not recognized.");
  }
}
