@Override public void onTimeSet(RadialPickerLayout view,int hour,int minute){
  WeekdayList days=WeekdayList.EVERY_DAY;
  if (reminder != null)   days=reminder.getDays();
  setReminder(new Reminder(hour,minute,days));
}
