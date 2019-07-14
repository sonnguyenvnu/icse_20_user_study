@NonNull public static SimpleDateFormat fromSkeleton(@NonNull String skeleton){
  Locale locale=Locale.getDefault();
  skeleton=getBestDateTimePattern(locale,skeleton);
  return DateFormats.fromSkeleton(skeleton,locale);
}
