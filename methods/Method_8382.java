private static void checkPickerDate(NumberPicker dayPicker,NumberPicker monthPicker,NumberPicker yearPicker){
  Calendar calendar=Calendar.getInstance();
  calendar.setTimeInMillis(System.currentTimeMillis());
  int currentYear=calendar.get(Calendar.YEAR);
  int currentMonth=calendar.get(Calendar.MONTH);
  int currentDay=calendar.get(Calendar.DAY_OF_MONTH);
  if (currentYear > yearPicker.getValue()) {
    yearPicker.setValue(currentYear);
  }
  if (yearPicker.getValue() == currentYear) {
    if (currentMonth > monthPicker.getValue()) {
      monthPicker.setValue(currentMonth);
    }
    if (currentMonth == monthPicker.getValue()) {
      if (currentDay > dayPicker.getValue()) {
        dayPicker.setValue(currentDay);
      }
    }
  }
}
