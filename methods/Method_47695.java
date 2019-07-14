@OnClick(R.id.tvReminderDays) void onWeekdayClicked(){
  if (reminder == null)   return;
  controller.onWeekdayClicked(reminder.getDays());
}
