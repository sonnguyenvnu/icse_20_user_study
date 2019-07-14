/** 
 * Rounds the given drawable with a  {@link RoundedBitmapDrawable} or {@link RoundedColorDrawable}. <p> If the given drawable is not a  {@link BitmapDrawable} or a {@link ColorDrawable}, it is returned without being rounded.
 * @return the rounded drawable, or the original drawable if rounding didn't take place
 */
private static Drawable applyLeafRounding(Drawable drawable,RoundingParams roundingParams,Resources resources){
  if (drawable instanceof BitmapDrawable) {
    final BitmapDrawable bitmapDrawable=(BitmapDrawable)drawable;
    RoundedBitmapDrawable roundedBitmapDrawable=new RoundedBitmapDrawable(resources,bitmapDrawable.getBitmap(),bitmapDrawable.getPaint());
    applyRoundingParams(roundedBitmapDrawable,roundingParams);
    return roundedBitmapDrawable;
  }
 else   if (drawable instanceof NinePatchDrawable) {
    final NinePatchDrawable ninePatchDrawableDrawable=(NinePatchDrawable)drawable;
    RoundedNinePatchDrawable roundedNinePatchDrawable=new RoundedNinePatchDrawable(ninePatchDrawableDrawable);
    applyRoundingParams(roundedNinePatchDrawable,roundingParams);
    return roundedNinePatchDrawable;
  }
 else   if (drawable instanceof ColorDrawable && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
    RoundedColorDrawable roundedColorDrawable=RoundedColorDrawable.fromColorDrawable((ColorDrawable)drawable);
    applyRoundingParams(roundedColorDrawable,roundingParams);
    return roundedColorDrawable;
  }
 else {
    FLog.w(TAG,"Don't know how to round that drawable: %s",drawable);
  }
  return drawable;
}
