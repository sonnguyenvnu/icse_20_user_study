private void copyAttributes(Habit model){
  Habit habit=habitList.getById(savedId);
  if (habit == null)   throw new RuntimeException("Habit not found");
  habit.copyFrom(model);
  habitList.update(habit);
  if (hasFrequencyChanged || hasTargetChanged)   habit.invalidateNewerThan(Timestamp.ZERO);
}
