@NonNull @Override protected String getContentType(@NonNull Context context,@NonNull Uri uri){
  return UriUtils.getMimeType(context,uri);
}
