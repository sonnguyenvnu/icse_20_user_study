@Override public synchronized void reorder(@NonNull Habit from,@NonNull Habit to){
  throwIfHasParent();
  if (order != BY_POSITION)   throw new IllegalStateException("cannot reorder automatically sorted list");
  if (indexOf(from) < 0)   throw new IllegalArgumentException("list does not contain (from) habit");
  int toPos=indexOf(to);
  if (toPos < 0)   throw new IllegalArgumentException("list does not contain (to) habit");
  list.remove(from);
  list.add(toPos,from);
  int position=0;
  for (  Habit h : list)   h.setPosition(position++);
  getObservable().notifyListeners();
}
