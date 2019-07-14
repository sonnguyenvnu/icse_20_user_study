public void onToggle(@NonNull Habit habit,Timestamp timestamp){
  commandRunner.execute(new ToggleRepetitionCommand(habitList,habit,timestamp),habit.getId());
}
