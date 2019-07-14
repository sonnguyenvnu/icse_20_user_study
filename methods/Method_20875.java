public static Bitmap getBitmap(final @NonNull View view,final int width,final int height){
  final Bitmap bitmap=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
  final Canvas canvas=new Canvas(bitmap);
  view.draw(canvas);
  return bitmap;
}
