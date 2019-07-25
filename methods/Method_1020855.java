public <T>T last(){
  Map<String,Object> sorting=(Map)options.get("sort");
  if (sorting == null)   sorting=map("_id",SortMap.get("desc"));
  options.put("sort",sorting);
  options.put("limit",1);
  List result=fetch();
  if (result == null)   return null;
  return (T)(result.get(0));
}
