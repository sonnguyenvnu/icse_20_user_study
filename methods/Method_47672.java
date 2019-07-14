protected void saveHabit(@NonNull Habit habit){
  if (originalHabit == null) {
    commandRunner.execute(component.getCreateHabitCommandFactory().create(habitList,habit),null);
  }
 else {
    commandRunner.execute(component.getEditHabitCommandFactory().create(habitList,originalHabit,habit),originalHabit.getId());
  }
}
