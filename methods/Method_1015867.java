@SuppressWarnings("unchecked") private <T>T get(String path,T def,Map submap){
  int index=path.indexOf('.');
  if (index == -1) {
    Object val=submap.get(path);
    if (val == null && def != null) {
      val=def;
      submap.put(path,def);
      save();
    }
    return (T)val;
  }
 else {
    String first=path.substring(0,index);
    String second=path.substring(index + 1,path.length());
    Map sub=(Map)submap.get(first);
    if (sub == null) {
      sub=new LinkedHashMap();
      submap.put(first,sub);
    }
    return get(second,def,sub);
  }
}
