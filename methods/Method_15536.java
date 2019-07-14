@NotNull @Override public Map<String,List<String>> getCombine(){
  List<String> andList=combine == null ? null : combine.get("&");
  if (andList == null) {
    andList=where == null ? new ArrayList<String>() : new ArrayList<String>(where.keySet());
    if (combine == null) {
      combine=new HashMap<>();
    }
    combine.put("&",andList);
  }
  return combine;
}
