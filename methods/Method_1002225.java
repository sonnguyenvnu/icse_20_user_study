@Override public boolean test(String url){
  if (Helpers.endsWith(url,FIRST_SCRIPT_NAME)) {
    return true;
  }
  if (Helpers.endsWith(url,LAST_SCRIPT_NAME)) {
    return true;
  }
  if (Helpers.endsWith(url,LAST_STYLE_NAME)) {
    return true;
  }
  return false;
}
