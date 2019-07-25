private void postpone(SharedPreferences prefs,Long alarm,String recurrenceRule){
  int pickerType=prefs.getBoolean("settings_simple_calendar",false) ? ReminderPickers.TYPE_AOSP : ReminderPickers.TYPE_GOOGLE;
  ReminderPickers reminderPicker=new ReminderPickers(this,this,pickerType);
  reminderPicker.pick(alarm,recurrenceRule);
  onDateSetListener=reminderPicker;
  onTimeSetListener=reminderPicker;
}
