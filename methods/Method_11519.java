private static boolean hasOpaqueBackground(View v){
  final Drawable bg=v.getBackground();
  return bg != null && bg.getOpacity() == PixelFormat.OPAQUE;
}
