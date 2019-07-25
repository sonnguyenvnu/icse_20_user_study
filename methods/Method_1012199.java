/** 
 * ????
 */
public void recycle(){
  if (headBanner != null) {
    headBanner.recycle();
  }
  if (footBanner != null) {
    footBanner.recycle();
  }
  clear();
}
