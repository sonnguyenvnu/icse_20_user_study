/** 
 * Call by  {@link CalendarPagerView} to indicate that a day was clicked and we should handle it.This method will always process the click to the selected date.
 * @param date date of the day that was clicked
 * @param nowSelected true if the date is now selected, false otherwise
 */
protected void onDateClicked(@NonNull CalendarDay date,boolean nowSelected){
switch (selectionMode) {
case SELECTION_MODE_MULTIPLE:
{
      adapter.setDateSelected(date,nowSelected);
      dispatchOnDateSelected(date,nowSelected);
    }
  break;
case SELECTION_MODE_RANGE:
{
  final List<CalendarDay> currentSelection=adapter.getSelectedDates();
  if (currentSelection.size() == 0) {
    adapter.setDateSelected(date,nowSelected);
    dispatchOnDateSelected(date,nowSelected);
  }
 else   if (currentSelection.size() == 1) {
    final CalendarDay firstDaySelected=currentSelection.get(0);
    if (firstDaySelected.equals(date)) {
      adapter.setDateSelected(date,nowSelected);
      dispatchOnDateSelected(date,nowSelected);
    }
 else     if (firstDaySelected.isAfter(date)) {
      adapter.selectRange(date,firstDaySelected);
      dispatchOnRangeSelected(adapter.getSelectedDates());
    }
 else {
      adapter.selectRange(firstDaySelected,date);
      dispatchOnRangeSelected(adapter.getSelectedDates());
    }
  }
 else {
    adapter.clearSelections();
    adapter.setDateSelected(date,nowSelected);
    dispatchOnDateSelected(date,nowSelected);
  }
}
break;
default :
case SELECTION_MODE_SINGLE:
{
adapter.clearSelections();
adapter.setDateSelected(date,true);
dispatchOnDateSelected(date,true);
}
break;
}
}
