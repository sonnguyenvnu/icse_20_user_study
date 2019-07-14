@Nullable public String getFile(){
  return isURL() ? null : fileOrUrl;
}
