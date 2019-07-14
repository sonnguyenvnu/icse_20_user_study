public void setHabit(@NonNull Habit habit){
  detachFrom(this.habit);
  attachTo(habit);
  this.habit=habit;
}
