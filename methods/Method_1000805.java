@SuppressWarnings("unchecked") public List<T> gets(){
  List<T> aList=new ArrayList<T>(names.length);
  for (  String name : names) {
    try {
      Plugin plugin=ioc.get(Plugin.class,name);
      if (plugin.canWork())       aList.add((T)plugin);
    }
 catch (    IocException e) {
    }
  }
  return aList;
}
