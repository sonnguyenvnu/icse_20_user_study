/** 
 * Swap bitmapBoard and bitmapBoardCached.
 */
private void swap(){
  lock.lock();
  Bitmap temp=bitmapBoardCached;
  bitmapBoardCached=bitmapBoard;
  bitmapBoard=temp;
  canvas.setBitmap(bitmapBoard);
  lock.unlock();
}
