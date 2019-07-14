public static String formatDate(ZonedDateTime dateTime,Context context){
  return formatDate(dateTime.toLocalDate(),dateTime.getZone(),context);
}
