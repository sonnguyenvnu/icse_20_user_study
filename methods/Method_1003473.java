public void pick(Long presetDateTime,String recurrenceRule){
  this.presetDateTime=DateUtils.getCalendar(presetDateTime).getTimeInMillis();
  this.recurrenceRule=recurrenceRule;
  if (pickerType == TYPE_AOSP) {
    timePickerCalledAlready=false;
    showDatePickerDialog(this.presetDateTime);
  }
 else {
    showDateTimeSelectors(this.presetDateTime);
  }
}
