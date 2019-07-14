/** 
 * Get the currently selected date, or null if no selection. Depending on the selection mode, you might get different results. <p>For  {@link #SELECTION_MODE_SINGLE}, returns the selected date.</p> <p>For  {@link #SELECTION_MODE_MULTIPLE}, returns the last date selected.</p> <p>For  {@link #SELECTION_MODE_RANGE}, returns the last date of the range. In most cases, you should probably be using  {@link #getSelectedDates()}.</p> <p>For  {@link #SELECTION_MODE_NONE}, returns null.</p>
 * @return The selected day, or null if no selection. If in multiple selection mode, thiswill return the last date of the list of selected dates.
 * @see MaterialCalendarView#getSelectedDates()
 */
@Nullable public CalendarDay getSelectedDate(){
  List<CalendarDay> dates=adapter.getSelectedDates();
  if (dates.isEmpty()) {
    return null;
  }
 else {
    return dates.get(dates.size() - 1);
  }
}
