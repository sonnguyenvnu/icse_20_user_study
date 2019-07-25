protected void LRD(String path,Object item){
  if (clude) {
    return;
  }
  int isFilter=0;
  for (  String p : items) {
    if (p.equals(path) || path.startsWith((p + ".")) || p.startsWith(path + ".") || path.startsWith((p + "[]")) || p.startsWith(path + "[]")) {
      isFilter++;
    }
  }
  if (isFilter == 0) {
    build.put(path,item,arrayIndex);
  }
}
