@NonNull public static String generateContent(@NonNull Context context,@NonNull String source,@Nullable String baseUrl,boolean dark,boolean isWiki,boolean replace){
  if (baseUrl == null) {
    return mergeContent(context,Jsoup.parse(source).html(),dark);
  }
 else {
    return mergeContent(context,parseReadme(source,baseUrl,isWiki),dark);
  }
}
