/** 
 * Perform a command and commit the transaction
 */
protected void commit(MetadataAction action){
  template.execute(createCallback(action,false));
}
