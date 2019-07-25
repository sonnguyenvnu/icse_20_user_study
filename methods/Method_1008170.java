/** 
 * Sets the settings and mappings as a single source.
 */
public CreateIndexRequest source(XContentBuilder source){
  return source(source.bytes(),source.contentType());
}
