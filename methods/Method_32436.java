private static String createMessage(long instantLocal,String zoneId){
  String localDateTime=DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS").print(new Instant(instantLocal));
  String zone=(zoneId != null ? " (" + zoneId + ")" : "");
  return "Illegal instant due to time zone offset transition (daylight savings time 'gap'): " + localDateTime + zone;
}
