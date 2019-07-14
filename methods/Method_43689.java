protected static Date parseDate(final String rawDate){
  String modified;
  if (rawDate.length() > 23) {
    modified=rawDate.substring(0,23);
  }
 else   if (rawDate.endsWith("Z")) {
switch (rawDate.length()) {
case 20:
      modified=rawDate.substring(0,19) + ".000";
    break;
case 22:
  modified=rawDate.substring(0,21) + "00";
break;
case 23:
modified=rawDate.substring(0,22) + "0";
break;
default :
modified=rawDate;
break;
}
}
 else {
switch (rawDate.length()) {
case 19:
modified=rawDate + ".000";
break;
case 21:
modified=rawDate + "00";
break;
case 22:
modified=rawDate + "0";
break;
default :
modified=rawDate;
break;
}
}
try {
SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
return dateFormat.parse(modified);
}
 catch (ParseException e) {
logger.warn("unable to parse rawDate={} modified={}",rawDate,modified,e);
return null;
}
}
