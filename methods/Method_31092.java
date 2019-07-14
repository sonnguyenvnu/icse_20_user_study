private static boolean hasSwDp(@Dimension(unit=Dimension.DP) int dp,@NonNull Context context){
  return context.getResources().getConfiguration().smallestScreenWidthDp >= dp;
}
