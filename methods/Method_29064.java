public String getClassNameByPath(String path){
  String className=path.substring(path.lastIndexOf('/'),path.length());
  className=className.replace(".class","").replace('/','.');
  return className;
}
