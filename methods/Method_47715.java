@Override public void showMessage(ShowHabitMenuBehavior.Message m){
switch (m) {
case COULD_NOT_EXPORT:
    showMessage(R.string.could_not_export);
case HABIT_DELETED:
  showMessage(R.string.delete_habits_message);
}
}
