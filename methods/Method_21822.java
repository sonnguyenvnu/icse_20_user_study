/** 
 * Set a  {@linkplain com.prolificinteractive.materialcalendarview.format.WeekDayFormatter}with the provided week day labels
 * @param weekDayLabels Labels to use for the days of the week
 * @see com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter
 * @see #setWeekDayFormatter(com.prolificinteractive.materialcalendarview.format.WeekDayFormatter)
 */
public void setWeekDayLabels(CharSequence[] weekDayLabels){
  setWeekDayFormatter(new ArrayWeekDayFormatter(weekDayLabels));
}
