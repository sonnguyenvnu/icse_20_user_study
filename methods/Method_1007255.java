/** 
 * Check if this handler supports actual event.
 * @param event file event fired by filesystem
 * @return true if supports - should continue handling
 */
public boolean accept(WatchFileEvent event){
  if (!event.isFile()) {
    return false;
  }
  if (isClassFileEvent() && (!event.getURI().toString().endsWith(".class") || event.getURI().toString().endsWith("_jsp.class"))) {
    return false;
  }
  return true;
}
