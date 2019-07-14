@TargetApi(Build.VERSION_CODES.LOLLIPOP) @Override public void setLayoutParams(ViewGroup.LayoutParams params){
  if (params instanceof ViewGroup.MarginLayoutParams && mUsingElevationCompat) {
    ((ViewGroup.MarginLayoutParams)params).leftMargin+=getShadowX();
    ((ViewGroup.MarginLayoutParams)params).topMargin+=getShadowY();
    ((ViewGroup.MarginLayoutParams)params).rightMargin+=getShadowX();
    ((ViewGroup.MarginLayoutParams)params).bottomMargin+=getShadowY();
  }
  super.setLayoutParams(params);
}
