@NonNull public static SimpleDateFormat fromSkeleton(@NonNull String skeleton,@NonNull Locale locale){
  SimpleDateFormat df=new SimpleDateFormat(skeleton,locale);
  df.setTimeZone(TimeZone.getTimeZone("UTC"));
  return df;
}
