@Override public int copy(ConstPool src,ConstPool dest,Map<String,String> map){
  String classname=src.getUtf8Info(name);
  if (map != null) {
    String newname=map.get(classname);
    if (newname != null)     classname=newname;
  }
  return dest.addClassInfo(classname);
}
