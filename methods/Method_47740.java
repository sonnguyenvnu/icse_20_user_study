private void showTimePicker(){
  final Calendar calendar=Calendar.getInstance();
  TimePickerDialog dialog=TimePickerDialog.newInstance((view,hour,minute) -> {
    reminderController.onSnoozeTimePicked(habit,hour,minute);
    finish();
  }
,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),DateFormat.is24HourFormat(this));
  dialog.show(getSupportFragmentManager(),"timePicker");
}
