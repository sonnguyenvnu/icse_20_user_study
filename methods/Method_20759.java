public static boolean isDateToday(final @NonNull DateTime dateTime){
  return dateTime.withZone(DateTimeZone.UTC).withTimeAtStartOfDay().equals(DateTime.now().withTimeAtStartOfDay().withZoneRetainFields(DateTimeZone.UTC));
}
