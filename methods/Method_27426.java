@NonNull private static CharSequence getDate(@Nullable Date date){
  return ParseDateFormat.getTimeAgo(date);
}
