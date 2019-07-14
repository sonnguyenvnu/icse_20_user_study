public IndexQueryBuilder setIndex(String indexName){
  Preconditions.checkArgument(StringUtils.isNotBlank(indexName));
  this.indexName=indexName;
  return this;
}
