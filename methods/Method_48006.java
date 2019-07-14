public void onDeleteHabit(){
  List<Habit> selected=Collections.singletonList(habit);
  screen.showDeleteConfirmationScreen(() -> {
    commandRunner.execute(new DeleteHabitsCommand(habitList,selected),null);
    screen.close();
  }
);
}
