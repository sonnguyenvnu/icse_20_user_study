@Override public Query nextQuery(){
  if (nextResults == null) {
    return null;
  }
  return Query.createWithNextPageQuery(nextResults);
}
