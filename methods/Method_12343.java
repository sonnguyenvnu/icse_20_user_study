static float getDensityScale(@NonNull Resources res,@DrawableRes @RawRes int id){
  final TypedValue value=new TypedValue();
  res.getValue(id,value,true);
  final int resourceDensity=value.density;
  final int density;
  if (resourceDensity == TypedValue.DENSITY_DEFAULT) {
    density=DisplayMetrics.DENSITY_DEFAULT;
  }
 else   if (resourceDensity != TypedValue.DENSITY_NONE) {
    density=resourceDensity;
  }
 else {
    density=0;
  }
  final int targetDensity=res.getDisplayMetrics().densityDpi;
  if (density > 0 && targetDensity > 0) {
    return (float)targetDensity / density;
  }
  return 1f;
}
