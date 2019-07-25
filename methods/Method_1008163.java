/** 
 * Associates a filter to the alias
 */
public Alias filter(Map<String,Object> filter){
  if (filter == null || filter.isEmpty()) {
    this.filter=null;
    return this;
  }
  try {
    XContentBuilder builder=XContentFactory.contentBuilder(XContentType.JSON);
    builder.map(filter);
    this.filter=builder.string();
    return this;
  }
 catch (  IOException e) {
    throw new ElasticsearchGenerationException("Failed to generate [" + filter + "]",e);
  }
}
