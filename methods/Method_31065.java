public static Uri.Builder appendId(Uri.Builder builder,long id){
  return builder.appendEncodedPath(String.valueOf(id));
}
