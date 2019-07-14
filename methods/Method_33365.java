void clearFocus(){
  LocalTime focusTime=timePicker.getValue();
  if (focusTime == null) {
    focusTime=LocalTime.now();
  }
  goToTime(focusTime);
}
