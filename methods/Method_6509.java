private void performReplace(String oldKey,String newKey){
  BitmapDrawable b=memCache.get(oldKey);
  replacedBitmaps.put(oldKey,newKey);
  if (b != null) {
    BitmapDrawable oldBitmap=memCache.get(newKey);
    boolean dontChange=false;
    if (oldBitmap != null && oldBitmap.getBitmap() != null && b.getBitmap() != null) {
      Bitmap oldBitmapObject=oldBitmap.getBitmap();
      Bitmap newBitmapObject=b.getBitmap();
      if (oldBitmapObject.getWidth() > newBitmapObject.getWidth() || oldBitmapObject.getHeight() > newBitmapObject.getHeight()) {
        dontChange=true;
      }
    }
    if (!dontChange) {
      ignoreRemoval=oldKey;
      memCache.remove(oldKey);
      memCache.put(newKey,b);
      ignoreRemoval=null;
    }
 else {
      memCache.remove(oldKey);
    }
  }
  Integer val=bitmapUseCounts.get(oldKey);
  if (val != null) {
    bitmapUseCounts.put(newKey,val);
    bitmapUseCounts.remove(oldKey);
  }
}
