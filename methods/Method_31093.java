private static boolean hasWDp(@Dimension(unit=Dimension.DP) int dp,@NonNull Context context){
  return context.getResources().getConfiguration().screenWidthDp >= dp;
}
