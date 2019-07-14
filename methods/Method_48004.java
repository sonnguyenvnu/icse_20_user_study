public void onToggleCheckmark(Timestamp timestamp){
  commandRunner.execute(new ToggleRepetitionCommand(habitList,habit,timestamp),null);
}
