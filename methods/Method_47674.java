@Nullable private Habit parseHabitFromArguments(){
  Bundle arguments=getArguments();
  if (arguments == null)   return null;
  Long id=(Long)arguments.get(BUNDLE_HABIT_ID);
  if (id == null)   return null;
  Habit habit=habitList.getById(id);
  if (habit == null)   throw new IllegalStateException();
  return habit;
}
