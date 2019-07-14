@Nullable private static Intent getTrending(@NonNull Context context,@NonNull Uri uri){
  List<String> segments=uri.getPathSegments();
  if (segments != null && !segments.isEmpty()) {
    if (uri.getPathSegments().get(0).equals("trending")) {
      String query="";
      String lang="";
      if (uri.getPathSegments().size() > 1) {
        lang=uri.getPathSegments().get(1);
      }
      if (uri.getQueryParameterNames() != null && !uri.getQueryParameterNames().isEmpty()) {
        query=uri.getQueryParameter("since");
      }
      return TrendingActivity.Companion.getTrendingIntent(context,lang,query);
    }
    return null;
  }
  return null;
}
