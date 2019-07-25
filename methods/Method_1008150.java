/** 
 * Sets the repository settings.
 * @param source repository settings in json or yaml format
 * @param xContentType the content type of the source
 * @return this request
 */
public PutRepositoryRequest settings(String source,XContentType xContentType){
  this.settings=Settings.builder().loadFromSource(source,xContentType).build();
  return this;
}
