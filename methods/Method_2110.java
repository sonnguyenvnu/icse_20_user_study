@Override public CloseableReference<Bitmap> process(Bitmap sourceBitmap,PlatformBitmapFactory bitmapFactory){
  final CloseableReference<Bitmap> bitmapRef=bitmapFactory.createBitmap(sourceBitmap.getWidth() / mScaleRatio,sourceBitmap.getHeight() / mScaleRatio);
  try {
    final Bitmap destBitmap=bitmapRef.get();
    final Canvas canvas=new Canvas(destBitmap);
    canvas.drawBitmap(sourceBitmap,null,new Rect(0,0,destBitmap.getWidth(),destBitmap.getHeight()),mPaint);
    NativeBlurFilter.iterativeBoxBlur(destBitmap,mIterations,Math.max(1,mBlurRadius / mScaleRatio));
    return CloseableReference.cloneOrNull(bitmapRef);
  }
  finally {
    CloseableReference.closeSafely(bitmapRef);
  }
}
