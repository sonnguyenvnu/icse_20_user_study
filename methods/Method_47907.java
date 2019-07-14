/** 
 * Returns the timestamps where there was a transition from performing a habit to not performing a habit, and vice-versa.
 * @param beginning the timestamp for the first checkmark
 * @param checks    the checkmarks, ordered by decresing timestamp
 * @return the list of transitions
 */
@NonNull protected ArrayList<Timestamp> getTransitions(Timestamp beginning,int[] checks){
  ArrayList<Timestamp> list=new ArrayList<>();
  Timestamp current=beginning;
  list.add(current);
  for (int i=1; i < checks.length; i++) {
    current=current.plus(1);
    int j=checks.length - i - 1;
    if ((checks[j + 1] == 0 && checks[j] > 0))     list.add(current);
    if ((checks[j + 1] > 0 && checks[j] == 0))     list.add(current.minus(1));
  }
  if (list.size() % 2 == 1)   list.add(current);
  return list;
}
