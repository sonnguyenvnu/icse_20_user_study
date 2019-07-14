public void onEdit(@NonNull Habit habit,Timestamp timestamp){
  CheckmarkList checkmarks=habit.getCheckmarks();
  double oldValue=checkmarks.getValues(timestamp,timestamp)[0];
  screen.showNumberPicker(oldValue / 1000,habit.getUnit(),newValue -> {
    newValue=Math.round(newValue * 1000);
    commandRunner.execute(new CreateRepetitionCommand(habit,timestamp,(int)newValue),habit.getId());
  }
);
}
