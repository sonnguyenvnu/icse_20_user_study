@NonNull @Override protected String getContentType(@NonNull Context context,@NonNull Pair<String,List<Uri>> stringListPair){
  return "multipart/form-data; boundary=" + BOUNDARY;
}
