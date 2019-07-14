@Override public int getItemPosition(@NonNull Object object){
  if (!(isInstanceOfView(object))) {
    return POSITION_NONE;
  }
  CalendarPagerView pagerView=(CalendarPagerView)object;
  CalendarDay firstViewDay=pagerView.getFirstViewDay();
  if (firstViewDay == null) {
    return POSITION_NONE;
  }
  int index=indexOf((V)object);
  if (index < 0) {
    return POSITION_NONE;
  }
  return index;
}
