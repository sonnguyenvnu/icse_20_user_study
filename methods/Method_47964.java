@Override public void onCommandExecuted(@NonNull Command command,@Nullable Long refreshKey){
  if (command instanceof ToggleRepetitionCommand) {
    ToggleRepetitionCommand toggleCmd=(ToggleRepetitionCommand)command;
    Habit habit=toggleCmd.getHabit();
    taskRunner.execute(() -> {
      if (habit.getCheckmarks().getTodayValue() != Checkmark.UNCHECKED)       cancel(habit);
    }
);
  }
  if (command instanceof DeleteHabitsCommand) {
    DeleteHabitsCommand deleteCommand=(DeleteHabitsCommand)command;
    List<Habit> deleted=deleteCommand.getSelected();
    for (    Habit habit : deleted)     cancel(habit);
  }
}
