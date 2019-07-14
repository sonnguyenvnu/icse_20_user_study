public static int calcDesiredSize(Context context,int parentWidth,int parentHeight){
  int orientation=context.getResources().getConfiguration().orientation;
  int desiredSize=(orientation == Configuration.ORIENTATION_LANDSCAPE) ? parentHeight / 2 : parentHeight / 3;
  return Math.min(desiredSize,parentWidth);
}
