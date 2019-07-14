@Override public String toString(){
  if (format != null) {
    if (format.equals("epoch")) {
      return String.valueOf(date.getTime());
    }
    if (format.equals("unix")) {
      return String.valueOf(date.getTime() / DIVIDE_MILLISECONDS_TO_SECONDS);
    }
    return formatCustom();
  }
  return timezoneName != null ? ISO8601Utils.format(date,false,TimeZone.getTimeZone(timezoneName)) : ISO8601Utils.format(date,false);
}
