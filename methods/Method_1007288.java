@Override public int copy(ConstPool src,ConstPool dest,Map<String,String> map){
  String packageName=src.getUtf8Info(name);
  int newName=dest.addUtf8Info(packageName);
  return dest.addModuleInfo(newName);
}
