@SuppressLint("NewApi") protected float dp2px(float dp){
  DisplayMetrics displayMetrics=getContext().getResources().getDisplayMetrics();
  return Math.round(dp * (displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
}
