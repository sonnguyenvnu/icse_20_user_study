/** 
 * Defines target folder where message will be moved.
 */
public ReceiverBuilder moveToFolder(final String targetFolder){
  this.markDeleted();
  this.targetFolder=targetFolder;
  return this;
}
