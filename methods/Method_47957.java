public void scheduleAtTime(@NonNull Habit habit,@NonNull Long reminderTime){
  if (reminderTime == null)   throw new IllegalArgumentException();
  if (!habit.hasReminder())   return;
  if (habit.isArchived())   return;
  long timestamp=getStartOfDay(removeTimezone(reminderTime));
  sys.scheduleShowReminder(reminderTime,habit,timestamp);
}
