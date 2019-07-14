public static Date parseDate(final String date){
  if (date.contains(".")) {
    return parseLongDate(date);
  }
 else {
    return parseShortDate(date);
  }
}
