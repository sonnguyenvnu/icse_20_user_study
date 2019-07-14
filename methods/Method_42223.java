public static float dpToPx(int dp,Context context){
  return dp * (context.getResources().getDisplayMetrics().density);
}
