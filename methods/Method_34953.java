@Override public boolean shouldVisit(Page referringPage,WebURL url){
  String href=url.getURL().toLowerCase();
  return !FILE_ENDING_EXCLUSION_PATTERN.matcher(href).matches();
}
