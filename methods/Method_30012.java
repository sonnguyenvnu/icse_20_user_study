public int getThemedDayOfMonthColor(Context context){
  int weekdayColor=ViewUtils.getColorFromAttrRes(android.R.attr.textColorPrimary,0,context);
  int weekendColor=ViewUtils.getColorFromAttrRes(android.R.attr.textColorHighlight,0,context);
  return getDayOfMonthColor(weekdayColor,weekendColor);
}
