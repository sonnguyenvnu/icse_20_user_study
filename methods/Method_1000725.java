protected void DLR(String path,Object item){
  if (clude) {
    if (items.contains(path)) {
      build.put(path,item,arrayIndex);
    }
  }
}
