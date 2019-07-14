public void addWidget(int widgetId,long habitIds[]){
  storage.putLongArray(getHabitIdKey(widgetId),habitIds);
}
