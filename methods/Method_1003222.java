@SuppressWarnings("unchecked") private Object get(String item){
  int dot=item.indexOf('.');
  if (dot >= 0) {
    String sub=item.substring(dot + 1);
    item=item.substring(0,dot);
    HashMap<String,Object> map=(HashMap<String,Object>)settings.get(item);
    if (map == null) {
      return "?" + item + "?";
    }
    return map.get(sub);
  }
  return settings.get(item);
}
