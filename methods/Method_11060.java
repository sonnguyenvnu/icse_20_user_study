/** 
 * Updates mDayView wheel. Sets max mDays according to selected mMonthView and mYearView
 */
private void updateDays(WheelView year,WheelView month,WheelView day){
  mCalendar.set(Calendar.YEAR,beginYear + year.getCurrentItem());
  mCalendar.set(Calendar.MONTH,month.getCurrentItem());
  int maxDay=mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
  int maxDays=RxTimeTool.getDaysByYearMonth(beginYear + year.getCurrentItem(),month.getCurrentItem() + 1);
  day.setViewAdapter(new DateNumericAdapter(mContext,1,maxDays,mCalendar.get(Calendar.DAY_OF_MONTH) - 1));
  int curDay=Math.min(maxDays,day.getCurrentItem() + 1);
  day.setCurrentItem(curDay - 1,true);
}
