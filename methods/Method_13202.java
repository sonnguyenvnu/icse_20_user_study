@Override public String getSourcePath(Container.Entry entry){
  String path=entry.getPath();
  int index=path.lastIndexOf('.');
  String prefix=(index == -1) ? path : path.substring(0,index);
  return prefix + ".java";
}
