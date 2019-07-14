public Map<String,String> getQueryMap(){
  if (queryMap == null) {
    queryMap=new HashMap<>();
  }
 else {
    queryMap.clear();
  }
  if (InputHelper.isEmpty(type) || "Select".equalsIgnoreCase(type)) {
    queryMap.remove(TYPE);
    queryMap.put(AFFILIATION,"owner,collaborator");
  }
 else {
    queryMap.remove(AFFILIATION);
    queryMap.put(TYPE,type.toLowerCase());
  }
  if (!isOrg) {
    if (sort.contains(" ")) {
      queryMap.put(SORT,sort.replace(" ","_").toLowerCase());
    }
 else {
      queryMap.put(SORT,sort.toLowerCase());
    }
    if (sortDirection.equals(sortDirectionList.get(0))) {
      queryMap.put(DIRECTION,sortDirection.toLowerCase().substring(0,4));
    }
 else {
      queryMap.put(DIRECTION,sortDirection.toLowerCase().substring(0,3));
    }
  }
  return queryMap;
}
