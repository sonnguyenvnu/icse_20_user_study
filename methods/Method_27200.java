@NonNull public static CharSequence getTimeAgo(@Nullable String toParse){
  try {
    Date parsedDate=getInstance().dateFormat.parse(toParse);
    long now=System.currentTimeMillis();
    return DateUtils.getRelativeTimeSpanString(parsedDate.getTime(),now,DateUtils.SECOND_IN_MILLIS);
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return "N/A";
}
