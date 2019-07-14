@Override public AbstractSQLConfig putWhere(String key,Object value,boolean prior){
  if (key != null) {
    if (where == null) {
      where=new LinkedHashMap<String,Object>();
    }
    if (value == null) {
      where.remove(key);
    }
 else {
      where.put(key,value);
    }
    combine=getCombine();
    List<String> andList=combine.get("&");
    if (value == null) {
      andList.remove(key);
    }
 else     if (andList == null || andList.contains(key) == false) {
      int i=0;
      if (andList == null) {
        andList=new ArrayList<>();
      }
 else       if (prior && andList.isEmpty() == false) {
        String idKey=getIdKey();
        String idInKey=idKey + "{}";
        String userIdKey=getUserIdKey();
        String userIdInKey=userIdKey + "{}";
        if (andList.contains(idKey)) {
          i++;
        }
        if (andList.contains(idInKey)) {
          i++;
        }
        if (andList.contains(userIdKey)) {
          i++;
        }
        if (andList.contains(userIdInKey)) {
          i++;
        }
      }
      if (prior) {
        andList.add(i,key);
      }
 else {
        andList.add(key);
      }
    }
    combine.put("&",andList);
  }
  return this;
}
