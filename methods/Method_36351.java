private List<String> getFormattedModuleInfo(String key){
  String ret=properties.getProperty(key);
  if (ret == null || ret.length() == 0) {
    return null;
  }
  String[] array=ret.split(",");
  List<String> list=new ArrayList<>(array.length);
  for (  String item : array) {
    int idx=item.indexOf(';');
    if (idx > -1) {
      item=item.substring(0,idx);
    }
    list.add(item.trim());
  }
  return list;
}
