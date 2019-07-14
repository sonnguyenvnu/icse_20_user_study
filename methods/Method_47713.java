@Override public void showEditHabitScreen(@NonNull Habit habit){
  activity.showDialog(editHabitDialogFactory.edit(habit),"editHabit");
}
