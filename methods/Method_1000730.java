/** 
 * ?????
 */
protected void DLR(String path,Object object){
  if (relation.containsKey(path)) {
    List<String> dests=relation.get(path);
    for (    String dest : dests) {
      if (dest.equals("")) {
        structure.put(path,object,arrayIndex);
        continue;
      }
      structure.put(dest,object,arrayIndex);
    }
  }
}
