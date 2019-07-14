private static int getSystemActionBarSize(Context context){
  TypedValue tv=new TypedValue();
  if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize,tv,true)) {
    return TypedValue.complexToDimensionPixelSize(tv.data,context.getResources().getDisplayMetrics());
  }
 else {
    return DensityUtil.dip2px(48);
  }
}
