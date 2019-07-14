/** 
 * Returns the path segment of the URL. 
 */
private String getPath(String url){
  int index=url.indexOf('/',7);
  if (index == -1) {
    return url;
  }
  String cleansed=url.substring(index + 1);
  for (int i=0; i < SEARCH_LIST.length; i++) {
    cleansed=StringUtils.replace(cleansed,SEARCH_LIST[i],REPLACEMENT_LIST[i]);
  }
  return cleansed;
}
