@Override public String getCacheGroup(){
  if (search != null || idList != null) {
    return null;
  }
  if (range == RANGE_ALL) {
    return "range=" + range;
  }
  return range == RANGE_USER || range == RANGE_MOMENT || range == RANGE_COMMENT ? null : "range=" + range + ";userId=" + id;
}
