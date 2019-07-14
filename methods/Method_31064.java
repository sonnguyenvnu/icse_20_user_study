public static long parseId(Uri uri){
  String last=uri.getLastPathSegment();
  return last == null ? -1 : Long.parseLong(last);
}
