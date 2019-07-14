public void onDeleteHabits(){
  List<Habit> selected=adapter.getSelected();
  screen.showDeleteConfirmationScreen(() -> {
    adapter.performRemove(selected);
    commandRunner.execute(new DeleteHabitsCommand(habitList,selected),null);
    adapter.clearSelection();
  }
);
}
