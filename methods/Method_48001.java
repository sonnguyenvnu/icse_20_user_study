public void onChangeColor(){
  List<Habit> selected=adapter.getSelected();
  Habit first=selected.get(0);
  screen.showColorPicker(first.getColor(),selectedColor -> {
    commandRunner.execute(new ChangeHabitColorCommand(habitList,selected,selectedColor),null);
    adapter.clearSelection();
  }
);
}
