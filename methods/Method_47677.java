private void setupReminderController(){
  reminderPanel.setController(new ReminderPanel.Controller(){
    @Override public void onTimeClicked(    int currentHour,    int currentMin){
      TimePickerDialog timePicker;
      boolean is24HourMode=DateFormat.is24HourFormat(getContext());
      timePicker=TimePickerDialog.newInstance(reminderPanel,currentHour,currentMin,is24HourMode);
      timePicker.show(getFragmentManager(),"timePicker");
    }
    @Override public void onWeekdayClicked(    WeekdayList currentDays){
      WeekdayPickerDialog dialog=new WeekdayPickerDialog();
      dialog.setListener(reminderPanel);
      dialog.setSelectedDays(currentDays);
      dialog.show(getFragmentManager(),"weekdayPicker");
    }
  }
);
}
