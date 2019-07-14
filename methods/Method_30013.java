public int getDayOfMonthColor(Context context){
  int weekdayColor=ContextCompat.getColor(context,R.color.primary_text_default_material_light);
  int weekendColor=ContextCompat.getColor(context,R.color.calendar_highlight_text);
  return getDayOfMonthColor(weekdayColor,weekendColor);
}
