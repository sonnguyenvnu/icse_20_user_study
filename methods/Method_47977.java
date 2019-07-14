public void remove(@NonNull Long id){
  Habit h=data.id_to_habit.get(id);
  if (h == null)   return;
  int position=data.habits.indexOf(h);
  data.habits.remove(position);
  data.id_to_habit.remove(id);
  data.checkmarks.remove(id);
  data.scores.remove(id);
  listener.onItemRemoved(position);
}
