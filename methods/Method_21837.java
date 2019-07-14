private void commit(State state){
  CalendarDay calendarDayToShow=null;
  if (adapter != null && state.cacheCurrentPosition) {
    calendarDayToShow=adapter.getItem(pager.getCurrentItem());
    if (calendarMode != state.calendarMode) {
      CalendarDay currentlySelectedDate=getSelectedDate();
      if (calendarMode == CalendarMode.MONTHS && currentlySelectedDate != null) {
        LocalDate lastVisibleCalendar=calendarDayToShow.getDate();
        CalendarDay lastVisibleCalendarDay=CalendarDay.from(lastVisibleCalendar.plusDays(1));
        if (currentlySelectedDate.equals(calendarDayToShow) || (currentlySelectedDate.isAfter(calendarDayToShow) && currentlySelectedDate.isBefore(lastVisibleCalendarDay))) {
          calendarDayToShow=currentlySelectedDate;
        }
      }
 else       if (calendarMode == CalendarMode.WEEKS) {
        LocalDate lastVisibleCalendar=calendarDayToShow.getDate();
        CalendarDay lastVisibleCalendarDay=CalendarDay.from(lastVisibleCalendar.plusDays(6));
        if (currentlySelectedDate != null && (currentlySelectedDate.equals(calendarDayToShow) || currentlySelectedDate.equals(lastVisibleCalendarDay) || (currentlySelectedDate.isAfter(calendarDayToShow) && currentlySelectedDate.isBefore(lastVisibleCalendarDay)))) {
          calendarDayToShow=currentlySelectedDate;
        }
 else {
          calendarDayToShow=lastVisibleCalendarDay;
        }
      }
    }
  }
  this.state=state;
  calendarMode=state.calendarMode;
  firstDayOfWeek=state.firstDayOfWeek;
  minDate=state.minDate;
  maxDate=state.maxDate;
  showWeekDays=state.showWeekDays;
  final CalendarPagerAdapter<?> newAdapter;
switch (calendarMode) {
case MONTHS:
    newAdapter=new MonthPagerAdapter(this);
  break;
case WEEKS:
newAdapter=new WeekPagerAdapter(this);
break;
default :
throw new IllegalArgumentException("Provided display mode which is not yet implemented");
}
if (adapter == null) {
adapter=newAdapter;
}
 else {
adapter=adapter.migrateStateAndReturn(newAdapter);
}
adapter.setShowWeekDays(showWeekDays);
pager.setAdapter(adapter);
setRangeDates(minDate,maxDate);
int tileHeight=showWeekDays ? calendarMode.visibleWeeksCount + DAY_NAMES_ROW : calendarMode.visibleWeeksCount;
pager.setLayoutParams(new LayoutParams(tileHeight));
setCurrentDate(selectionMode == SELECTION_MODE_SINGLE && !adapter.getSelectedDates().isEmpty() ? adapter.getSelectedDates().get(0) : CalendarDay.today());
if (calendarDayToShow != null) {
pager.setCurrentItem(adapter.getIndexForDay(calendarDayToShow));
}
invalidateDecorators();
updateUi();
}
