@Override public void onCommandExecuted(@NonNull Command command,@Nullable Long refreshKey){
  if (command instanceof ToggleRepetitionCommand)   return;
  if (command instanceof ChangeHabitColorCommand)   return;
  scheduleAll();
}
