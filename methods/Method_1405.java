@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1) @Override public Bitmap createNakedBitmap(int width,int height,Bitmap.Config bitmapConfig){
  CloseableReference<PooledByteBuffer> jpgRef=mJpegGenerator.generate((short)width,(short)height);
  EncodedImage encodedImage=null;
  CloseableReference<byte[]> encodedBytesArrayRef=null;
  try {
    encodedImage=new EncodedImage(jpgRef);
    encodedImage.setImageFormat(DefaultImageFormats.JPEG);
    BitmapFactory.Options options=getBitmapFactoryOptions(encodedImage.getSampleSize(),bitmapConfig);
    int length=jpgRef.get().size();
    final PooledByteBuffer pooledByteBuffer=jpgRef.get();
    encodedBytesArrayRef=mFlexByteArrayPool.get(length + 2);
    byte[] encodedBytesArray=encodedBytesArrayRef.get();
    pooledByteBuffer.read(0,encodedBytesArray,0,length);
    Bitmap bitmap=BitmapFactory.decodeByteArray(encodedBytesArray,0,length,options);
    bitmap.setHasAlpha(true);
    bitmap.eraseColor(Color.TRANSPARENT);
    return bitmap;
  }
  finally {
    CloseableReference.closeSafely(encodedBytesArrayRef);
    EncodedImage.closeSafely(encodedImage);
    CloseableReference.closeSafely(jpgRef);
  }
}
