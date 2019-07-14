@NonNull public static String generateContent(@NonNull String source,String theme){
  return getHtmlContent(theme,getFormattedSource(source),false,false);
}
