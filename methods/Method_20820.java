public static @NonNull String flooredPercentage(final float value,final @NonNull Locale locale){
  final NumberFormat numberFormat=NumberFormat.getPercentInstance(locale);
  numberFormat.setRoundingMode(RoundingMode.DOWN);
  return numberFormat.format(value / 100);
}
