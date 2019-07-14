public void populateFrom(@NonNull Habit habit){
  Resources res=getResources();
  if (habit.isNumerical())   tvDescription.setExample(res.getString(R.string.example_question_numerical));
 else   tvDescription.setExample(res.getString(R.string.example_question_boolean));
  setColor(habit.getColor());
  tvName.setText(habit.getName());
  tvDescription.setRealText(habit.getDescription());
}
