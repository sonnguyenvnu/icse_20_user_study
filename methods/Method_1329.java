private @Nullable static String extractExtension(String path){
  int pos=path.lastIndexOf('.');
  if (pos < 0 || pos == path.length() - 1) {
    return null;
  }
  return path.substring(pos + 1);
}
