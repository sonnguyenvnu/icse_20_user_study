protected boolean isFilter(List<String> filters){
  boolean filter=false;
  if (filters == null || filters.size() == 0) {
    filter=true;
  }
 else {
    filter=filters.contains(path.path);
  }
  return filter;
}
