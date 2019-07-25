/** 
 * Perform a read only command
 */
protected void read(MetadataAction action){
  template.execute(createCallback(action,true));
}
