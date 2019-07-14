@NonNull public static CharSequence getTimeAgo(@Nullable Date parsedDate){
  if (parsedDate != null) {
    long now=System.currentTimeMillis();
    return DateUtils.getRelativeTimeSpanString(parsedDate.getTime(),now,DateUtils.SECOND_IN_MILLIS);
  }
  return "N/A";
}
