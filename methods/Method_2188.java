@DoNotStrip public static Bitmap hookDecodeResourceStream(Resources res,TypedValue value,InputStream is,Rect pad,BitmapFactory.Options opts){
  if (opts == null) {
    opts=new BitmapFactory.Options();
  }
  if (opts.inDensity == 0 && value != null) {
    final int density=value.density;
    if (density == TypedValue.DENSITY_DEFAULT) {
      opts.inDensity=DisplayMetrics.DENSITY_DEFAULT;
    }
 else     if (density != TypedValue.DENSITY_NONE) {
      opts.inDensity=density;
    }
  }
  if (opts.inTargetDensity == 0 && res != null) {
    opts.inTargetDensity=res.getDisplayMetrics().densityDpi;
  }
  return hookDecodeStream(is,pad,opts);
}
