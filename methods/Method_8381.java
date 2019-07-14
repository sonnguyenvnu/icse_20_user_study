private static void updateDayPicker(NumberPicker dayPicker,NumberPicker monthPicker,NumberPicker yearPicker){
  Calendar calendar=Calendar.getInstance();
  calendar.set(Calendar.MONTH,monthPicker.getValue());
  calendar.set(Calendar.YEAR,yearPicker.getValue());
  dayPicker.setMinValue(1);
  dayPicker.setMaxValue(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
}
