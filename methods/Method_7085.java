@Nullable @Override public String[] getStreamTypes(@NonNull Uri uri,@NonNull String mimeTypeFilter){
  if (mimeTypeFilter.startsWith("*/") || mimeTypeFilter.startsWith("image/"))   return new String[]{"image/jpeg","image/png","image/webp"};
  return null;
}
