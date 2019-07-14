@Override public void onWeekdaysSet(WeekdayList selectedDays){
  if (reminder == null)   return;
  if (selectedDays.isEmpty())   selectedDays=WeekdayList.EVERY_DAY;
  setReminder(new Reminder(reminder.getHour(),reminder.getMinute(),selectedDays));
}
