void init(){
  calendarPlaceHolder.setOpacity(1);
  if (unit.get() == TimeUnit.HOURS) {
    selectedHourLabel.setTextFill(Color.rgb(255,255,255,0.87));
  }
 else {
    selectedMinLabel.setTextFill(Color.rgb(255,255,255,0.87));
  }
}
