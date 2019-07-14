@OnClick(R.id.tvReminderTime) void onDateSpinnerClick(){
  int hour=8;
  int min=0;
  if (reminder != null) {
    hour=reminder.getHour();
    min=reminder.getMinute();
  }
  controller.onTimeClicked(hour,min);
}
