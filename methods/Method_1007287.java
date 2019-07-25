@Override public int copy(ConstPool src,ConstPool dest,Map<String,String> map){
  String moduleName=src.getUtf8Info(name);
  int newName=dest.addUtf8Info(moduleName);
  return dest.addModuleInfo(newName);
}
