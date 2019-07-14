@CheckResult public static Toast custom(@NonNull Context context,@NonNull String message,@DrawableRes int iconRes,@ColorInt int textColor,@ColorInt int tintColor,int duration,boolean withIcon,boolean shouldTint){
  return custom(context,message,getDrawable(context,iconRes),textColor,tintColor,duration,withIcon,shouldTint);
}
