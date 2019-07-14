/** 
 * Call by  {@link CalendarPagerView} to indicate that a day was clicked and we should handle it
 */
protected void onDateClicked(final DayView dayView){
  final CalendarDay currentDate=getCurrentDate();
  final CalendarDay selectedDate=dayView.getDate();
  final int currentMonth=currentDate.getMonth();
  final int selectedMonth=selectedDate.getMonth();
  if (calendarMode == CalendarMode.MONTHS && allowClickDaysOutsideCurrentMonth && currentMonth != selectedMonth) {
    if (currentDate.isAfter(selectedDate)) {
      goToPrevious();
    }
 else     if (currentDate.isBefore(selectedDate)) {
      goToNext();
    }
  }
  onDateClicked(dayView.getDate(),!dayView.isChecked());
}
