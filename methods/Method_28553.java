@NonNull public static String generateContent(@NonNull String source,boolean isDark,boolean wrap){
  return getHtmlContent(getStyle(isDark),getFormattedSource(source),wrap,isDark);
}
