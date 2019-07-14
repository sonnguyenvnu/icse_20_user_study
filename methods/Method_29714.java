public byte[] getJpegAndFree(int quality){
  final byte[] jpeg=getJpeg(quality);
  freeBitmap();
  return jpeg;
}
