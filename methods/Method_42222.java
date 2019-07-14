public static int pxToDp(int px,Context c){
  DisplayMetrics displayMetrics=c.getResources().getDisplayMetrics();
  return Math.round(px * (displayMetrics.ydpi / DisplayMetrics.DENSITY_DEFAULT));
}
