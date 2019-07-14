@Override protected void refreshData(){
  Habit habit=getHabit();
  int color=PaletteUtils.getColor(getContext(),habit.getColor());
  reminderLabel.setText(getResources().getString(R.string.reminder_off));
  questionLabel.setVisibility(VISIBLE);
  questionLabel.setTextColor(color);
  questionLabel.setText(habit.getDescription());
  frequencyLabel.setText(toText(habit.getFrequency()));
  if (habit.hasReminder())   updateReminderText(habit.getReminder());
  if (habit.getDescription().isEmpty())   questionLabel.setVisibility(GONE);
  invalidate();
}
