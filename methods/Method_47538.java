public static float spToPixels(Context context,float sp){
  if (fixedResolution != null)   return sp * fixedResolution;
  Resources resources=context.getResources();
  DisplayMetrics metrics=resources.getDisplayMetrics();
  return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp,metrics);
}
