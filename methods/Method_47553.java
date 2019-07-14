private void updateDisplay(boolean announce){
  if (mDayOfWeekView != null) {
    mDayOfWeekView.setText(mCalendar.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG,Locale.getDefault()).toUpperCase(Locale.getDefault()));
  }
  mSelectedMonthTextView.setText(mCalendar.getDisplayName(Calendar.MONTH,Calendar.SHORT,Locale.getDefault()).toUpperCase(Locale.getDefault()));
  mSelectedDayTextView.setText(DAY_FORMAT.format(mCalendar.getTime()));
  mYearView.setText(YEAR_FORMAT.format(mCalendar.getTime()));
  long millis=mCalendar.getTimeInMillis();
  mAnimator.setDateMillis(millis);
  int flags=DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_NO_YEAR;
  String monthAndDayText=DateUtils.formatDateTime(getActivity(),millis,flags);
  mMonthAndDayView.setContentDescription(monthAndDayText);
  if (announce) {
    flags=DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR;
    String fullDateText=DateUtils.formatDateTime(getActivity(),millis,flags);
    Utils.tryAccessibilityAnnounce(mAnimator,fullDateText);
  }
}
