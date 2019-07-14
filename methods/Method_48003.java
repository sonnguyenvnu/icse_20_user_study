public void onUnarchiveHabits(){
  commandRunner.execute(new UnarchiveHabitsCommand(habitList,adapter.getSelected()),null);
  adapter.clearSelection();
}
