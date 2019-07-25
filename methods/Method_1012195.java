/** 
 * ???????????
 */
public void recycle(){
  for (  Broccoli broccoli : mBroccoliMap.values()) {
    broccoli.removeAllPlaceholders();
  }
  mBroccoliMap.clear();
  clear();
}
