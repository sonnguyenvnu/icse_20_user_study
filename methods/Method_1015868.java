@SuppressWarnings("unchecked") private void set(String path,Object val,Map submap){
  int index=path.indexOf('.');
  if (index == -1) {
    if (val == null) {
      submap.remove(path);
    }
 else {
      submap.put(path,val);
    }
    save();
  }
 else {
    String first=path.substring(0,index);
    String second=path.substring(index + 1,path.length());
    Map sub=(Map)submap.get(first);
    if (sub == null) {
      sub=new LinkedHashMap();
      submap.put(first,sub);
    }
    set(second,val,sub);
  }
}
