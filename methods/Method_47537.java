public static float dpToPixels(Context context,float dp){
  if (fixedResolution != null)   return dp * fixedResolution;
  Resources resources=context.getResources();
  DisplayMetrics metrics=resources.getDisplayMetrics();
  return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,metrics);
}
