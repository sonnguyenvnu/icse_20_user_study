/** 
 * Removes all the habits from the list.
 */
public void removeAll(){
  List<Habit> copy=new LinkedList<>();
  for (  Habit h : this)   copy.add(h);
  for (  Habit h : copy)   remove(h);
  observable.notifyListeners();
}
