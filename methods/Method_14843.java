@Override public String getCacheGroup(){
  if (range == RANGE_ALL) {
    return search != null ? null : "range=" + range;
  }
  return range == RANGE_SINGLE || search != null ? null : "range=" + range + ";userId=" + id;
}
