/** 
 * Sets the settings and mappings as a single source.
 */
public CreateIndexRequest source(BytesReference source,XContentType xContentType){
  Objects.requireNonNull(xContentType);
  source(XContentHelper.convertToMap(source,false,xContentType).v2());
  return this;
}
