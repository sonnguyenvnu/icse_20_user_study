private void updateReminderText(Reminder reminder){
  reminderLabel.setText(AndroidDateUtils.formatTime(getContext(),reminder.getHour(),reminder.getMinute()));
}
