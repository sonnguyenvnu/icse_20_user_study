public EditHabitDialog createBoolean(){
  EditHabitDialog dialog=new EditHabitDialog();
  Bundle args=new Bundle();
  args.putInt(BUNDLE_HABIT_TYPE,Habit.YES_NO_HABIT);
  dialog.setArguments(args);
  return dialog;
}
