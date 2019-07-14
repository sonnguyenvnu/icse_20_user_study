/** 
 * Select or un-select a day.
 * @param day Day to select or un-select
 * @param selected Whether to select or un-select the day from the list.
 * @see CalendarPagerAdapter#selectRange(CalendarDay,CalendarDay)
 */
public void setDateSelected(CalendarDay day,boolean selected){
  if (selected) {
    if (!selectedDates.contains(day)) {
      selectedDates.add(day);
      invalidateSelectedDates();
    }
  }
 else {
    if (selectedDates.contains(day)) {
      selectedDates.remove(day);
      invalidateSelectedDates();
    }
  }
}
