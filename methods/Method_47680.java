public EditHabitDialog edit(@NonNull Habit habit){
  if (habit.getId() == null)   throw new IllegalArgumentException("habit not saved");
  EditHabitDialog dialog=new EditHabitDialog();
  Bundle args=new Bundle();
  args.putLong(BUNDLE_HABIT_ID,habit.getId());
  args.putInt(BUNDLE_HABIT_TYPE,habit.getType());
  dialog.setArguments(args);
  return dialog;
}
