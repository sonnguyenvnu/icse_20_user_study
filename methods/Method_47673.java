@OnClick(R.id.buttonSave) void onSaveButtonClick(){
  int type=getTypeFromArguments();
  if (!namePanel.validate())   return;
  if (type == Habit.YES_NO_HABIT && !frequencyPanel.validate())   return;
  if (type == Habit.NUMBER_HABIT && !targetPanel.validate())   return;
  Habit habit=modelFactory.buildHabit();
  if (originalHabit != null)   habit.copyFrom(originalHabit);
  habit.setName(namePanel.getName());
  habit.setDescription(namePanel.getDescription());
  habit.setColor(namePanel.getColor());
  habit.setReminder(reminderPanel.getReminder());
  habit.setFrequency(frequencyPanel.getFrequency());
  habit.setUnit(targetPanel.getUnit());
  habit.setTargetValue(targetPanel.getTargetValue());
  habit.setType(type);
  saveHabit(habit);
  dismiss();
}
