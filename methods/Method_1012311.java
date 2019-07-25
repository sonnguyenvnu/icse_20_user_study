@CheckResult public static Toast warning(@NonNull Context context,@StringRes int message,int duration,boolean withIcon){
  return custom(context,context.getString(message),Utils.getDrawable(context,R.drawable.xtoast_ic_error_outline_white_24dp),Utils.getColor(context,R.color.toast_warning_color),Utils.getColor(context,R.color.toast_default_text_color),duration,withIcon,true);
}
