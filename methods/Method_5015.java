/** 
 * Returns whether the manager has completed initialization. 
 */
public boolean isInitialized(){
  Assertions.checkState(!released);
  return initialized;
}
