private void storeBitmap(final byte[] bitmap){
  if (handler != null)   freeBitmap();
  handler=jniStoreBitmapData(bitmap);
}
