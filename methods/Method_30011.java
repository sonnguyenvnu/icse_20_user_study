public int getDayOfMonthColor(int weekdayColor,int weekendColor){
  DayOfWeek dayOfWeek=getDate().getDayOfWeek();
  boolean isWeekend=dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
  return isWeekend ? weekendColor : weekdayColor;
}
