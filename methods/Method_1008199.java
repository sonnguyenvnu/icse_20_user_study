/** 
 * Sets the document to index in bytes form.
 */
public IndexRequest source(BytesReference source,XContentType xContentType){
  this.source=Objects.requireNonNull(source);
  this.contentType=Objects.requireNonNull(xContentType);
  return this;
}
