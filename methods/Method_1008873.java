/** 
 * Clears font cache
 */
public void clear(){
synchronized (changeLock) {
    if (log.isTraceEnabled()) {
      log.trace("Font cache cleared.");
    }
    fontfileMap=null;
    failedFontMap=null;
    changed=true;
  }
}
