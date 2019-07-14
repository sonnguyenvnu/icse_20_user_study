public static float applyDimensionDp(Context context,float value){
  return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value,context.getResources().getDisplayMetrics());
}
