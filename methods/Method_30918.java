public static int getColumnCount(Context context){
  return ViewUtils.hasSw600Dp(context) ? (ViewUtils.hasW960Dp(context) ? 3 : 2) : ViewUtils.hasW600Dp(context) ? 2 : 1;
}
