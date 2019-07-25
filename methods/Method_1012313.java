@CheckResult public static Toast custom(@NonNull Context context,@NonNull CharSequence message,@DrawableRes int iconRes,@ColorRes int tintColorRes,int duration,boolean withIcon,boolean shouldTint){
  return custom(context,message,Utils.getDrawable(context,iconRes),Utils.getColor(context,tintColorRes),Utils.getColor(context,R.color.toast_default_text_color),duration,withIcon,shouldTint);
}
