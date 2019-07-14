public static @Nullable ImageRequest fromFile(@Nullable File file){
  return (file == null) ? null : ImageRequest.fromUri(UriUtil.getUriForFile(file));
}
