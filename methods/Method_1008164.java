/** 
 * Associates a filter to the alias
 */
public Alias filter(QueryBuilder filterBuilder){
  if (filterBuilder == null) {
    this.filter=null;
    return this;
  }
  try {
    XContentBuilder builder=XContentFactory.jsonBuilder();
    filterBuilder.toXContent(builder,ToXContent.EMPTY_PARAMS);
    builder.close();
    this.filter=builder.string();
    return this;
  }
 catch (  IOException e) {
    throw new ElasticsearchGenerationException("Failed to build json for alias request",e);
  }
}
