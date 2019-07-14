public static void prefetch(Uri... uris){
  if (uris == null) {
    return;
  }
  ImageLoader imageLoader=imageLoader();
  for (  Uri uri : uris) {
    imageLoader.prefetch(uri);
  }
}
