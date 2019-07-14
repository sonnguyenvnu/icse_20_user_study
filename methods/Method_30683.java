public static boolean shouldUseWideLayout(Context context){
  return ViewUtils.hasSw600Dp(context) ? ViewUtils.isInLandscape(context) : !CardUtils.isFullWidth(context);
}
