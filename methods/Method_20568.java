private @NonNull String pathAndQueryFromURL(final @NonNull URL url){
  return url.getPath() + "?" + url.getQuery();
}
