@DoNotStrip @SuppressLint("NewApi") private static boolean shouldPremultiply(BitmapFactory.Options options){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && options != null) {
    return options.inPremultiplied;
  }
  return true;
}
