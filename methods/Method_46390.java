public static float dp2px(final Context context,final float dpValue){
  return dpValue * context.getResources().getDisplayMetrics().density;
}
