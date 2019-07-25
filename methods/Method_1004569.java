/** 
 * classes that extend MapTileProviderBase must call this method to prevent memory leaks. Updated 5.2+
 */
public void detach(){
  BitmapPool.getInstance().asyncRecycle(mTileNotFoundImage);
  mTileNotFoundImage=null;
  clearTileCache();
}
