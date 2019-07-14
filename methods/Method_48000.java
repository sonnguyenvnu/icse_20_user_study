public void onArchiveHabits(){
  commandRunner.execute(new ArchiveHabitsCommand(habitList,adapter.getSelected()),null);
  adapter.clearSelection();
}
