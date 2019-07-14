private void populateForm(){
  Habit habit=modelFactory.buildHabit();
  habit.setFrequency(Frequency.DAILY);
  habit.setColor(prefs.getDefaultHabitColor(habit.getColor()));
  habit.setType(getTypeFromArguments());
  if (originalHabit != null)   habit.copyFrom(originalHabit);
  if (habit.isNumerical())   frequencyPanel.setVisibility(GONE);
 else   targetPanel.setVisibility(GONE);
  namePanel.populateFrom(habit);
  frequencyPanel.setFrequency(habit.getFrequency());
  targetPanel.setTargetValue(habit.getTargetValue());
  targetPanel.setUnit(habit.getUnit());
  if (habit.hasReminder())   reminderPanel.setReminder(habit.getReminder());
}
