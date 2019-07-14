/** 
 * Clear the previous selection, select the range of days from first to last, and finally invalidate. First day should be before last day, otherwise the selection won't happen.
 * @param first The first day of the range.
 * @param last The last day in the range.
 * @see CalendarPagerAdapter#setDateSelected(CalendarDay,boolean)
 */
public void selectRange(final CalendarDay first,final CalendarDay last){
  selectedDates.clear();
  LocalDate temp=LocalDate.of(first.getYear(),first.getMonth(),first.getDay());
  final LocalDate end=last.getDate();
  while (temp.isBefore(end) || temp.equals(end)) {
    selectedDates.add(CalendarDay.from(temp));
    temp=temp.plusDays(1);
  }
  invalidateSelectedDates();
}
