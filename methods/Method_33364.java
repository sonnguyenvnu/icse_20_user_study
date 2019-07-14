private void goToTime(LocalTime time){
  if (time != null) {
    int hour=time.getHour();
    selectedHourLabel.setText(Integer.toString(hour % (is24HourView ? 24 : 12) == 0 ? (is24HourView ? 0 : 12) : hour % (is24HourView ? 24 : 12)));
    selectedMinLabel.setText(unitConverter.toString(time.getMinute()));
    if (!is24HourView) {
      period.set(hour < 12 ? "AM" : "PM");
    }
    minsPointerRotate.setAngle(180 + (time.getMinute() + 45) % 60 * Math.toDegrees(2 * Math.PI / 60));
    hoursPointerRotate.setAngle(180 + Math.toDegrees(2 * (hour - 3) * Math.PI / 12));
    _24HourHoursPointerRotate.setAngle(180 + Math.toDegrees(2 * (hour - 3) * Math.PI / 12));
  }
}
