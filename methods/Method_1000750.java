/** 
 * ???name, type??
 * @param path
 */
private void init(String path){
  String key=fetchNode(path);
  if (isList(key)) {
    type=TYPE_LIST;
    name=key.substring(0,key.indexOf(LIST_SEPARATOR));
    return;
  }
  name=key;
}
