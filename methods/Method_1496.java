@Override public void onNewResultImpl(DataSource<List<CloseableReference<CloseableImage>>> dataSource){
  if (!dataSource.isFinished()) {
    return;
  }
  List<CloseableReference<CloseableImage>> imageRefList=dataSource.getResult();
  if (imageRefList == null) {
    onNewResultListImpl(null);
    return;
  }
  try {
    List<Bitmap> bitmapList=new ArrayList<>(imageRefList.size());
    for (    CloseableReference<CloseableImage> closeableImageRef : imageRefList) {
      if (closeableImageRef != null && closeableImageRef.get() instanceof CloseableBitmap) {
        bitmapList.add(((CloseableBitmap)closeableImageRef.get()).getUnderlyingBitmap());
      }
 else {
        bitmapList.add(null);
      }
    }
    onNewResultListImpl(bitmapList);
  }
  finally {
    for (    CloseableReference<CloseableImage> closeableImageRef : imageRefList) {
      CloseableReference.closeSafely(closeableImageRef);
    }
  }
}
