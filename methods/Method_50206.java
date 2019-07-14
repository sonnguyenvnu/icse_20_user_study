/** 
 * ?????Sticky??
 */
public void removeAllStickyEvents(){
synchronized (mStickyEventMap) {
    mStickyEventMap.clear();
  }
}
