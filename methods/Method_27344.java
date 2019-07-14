@Nullable private static String getGistId(@NonNull Uri uri){
  List<String> segments=uri.getPathSegments();
  if (segments.size() != 1 && segments.size() != 2)   return null;
  String gistId=segments.get(segments.size() - 1);
  if (InputHelper.isEmpty(gistId))   return null;
  if (TextUtils.isDigitsOnly(gistId))   return gistId;
 else   if (gistId.matches("[a-fA-F0-9]+"))   return gistId;
 else   return null;
}
