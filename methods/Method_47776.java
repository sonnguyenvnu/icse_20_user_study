protected List<Habit> getHabitsFromWidgetId(int widgetId){
  long selectedIds[]=widgetPrefs.getHabitIdsFromWidgetId(widgetId);
  ArrayList<Habit> selectedHabits=new ArrayList<>(selectedIds.length);
  for (  long id : selectedIds) {
    Habit h=habits.getById(id);
    if (h == null)     throw new HabitNotFoundException();
    selectedHabits.add(h);
  }
  return selectedHabits;
}
