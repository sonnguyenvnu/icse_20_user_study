/** 
 * Returns if the entry is in the Main space's protected queue. 
 */
public boolean inMainProtected(){
  return getQueueType() == PROTECTED;
}
