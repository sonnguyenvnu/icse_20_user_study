@NonNull private String getIdName(){
  if (cacheIdName == null) {
    String id=getTableAnnotation().id();
    if (id.isEmpty())     throw new RuntimeException("Table id is empty");
    cacheIdName=id;
  }
  return cacheIdName;
}
