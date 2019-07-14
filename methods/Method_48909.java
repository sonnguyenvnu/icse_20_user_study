public IndexQueryBuilder setQuery(String query){
  Preconditions.checkArgument(StringUtils.isNotBlank(query));
  this.query=query;
  return this;
}
