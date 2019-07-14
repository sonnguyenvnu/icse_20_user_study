public EditHabitDialog createNumerical(){
  EditHabitDialog dialog=new EditHabitDialog();
  Bundle args=new Bundle();
  args.putInt(BUNDLE_HABIT_TYPE,Habit.NUMBER_HABIT);
  dialog.setArguments(args);
  return dialog;
}
