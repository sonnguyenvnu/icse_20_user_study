/** 
 * The settings to create the index template with (either json/yaml format).
 */
public PutIndexTemplateRequest settings(String source,XContentType xContentType){
  this.settings=Settings.builder().loadFromSource(source,xContentType).build();
  return this;
}
