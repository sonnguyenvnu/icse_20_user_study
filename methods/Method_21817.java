/** 
 * Clear the currently selected date(s)
 */
public void clearSelection(){
  List<CalendarDay> dates=getSelectedDates();
  adapter.clearSelections();
  for (  CalendarDay day : dates) {
    dispatchOnDateSelected(day,false);
  }
}
