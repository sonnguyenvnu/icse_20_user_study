public synchronized void scheduleAll(){
  HabitList reminderHabits=habitList.getFiltered(HabitMatcher.WITH_ALARM);
  for (  Habit habit : reminderHabits)   schedule(habit);
}
