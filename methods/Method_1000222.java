public static Bitmap resize(InputStream stream,int scaledWidth,int scaledHeight){
  final Bitmap bitmap=BitmapFactory.decodeStream(stream);
  return Bitmap.createScaledBitmap(bitmap,scaledWidth,scaledHeight,true);
}
