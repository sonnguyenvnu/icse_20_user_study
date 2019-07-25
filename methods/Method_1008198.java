/** 
 * Sets the document source to index. Note, its preferable to either set it using  {@link #source(org.elasticsearch.common.xcontent.XContentBuilder)}or using the  {@link #source(byte[],XContentType)}.
 */
public IndexRequest source(String source,XContentType xContentType){
  return source(new BytesArray(source),xContentType);
}
