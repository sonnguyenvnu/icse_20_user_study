/** 
 * Sets the settings to be updated (either json or yaml format)
 */
public UpdateSettingsRequest settings(String source,XContentType xContentType){
  this.settings=Settings.builder().loadFromSource(source,xContentType).build();
  return this;
}
