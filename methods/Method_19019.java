/** 
 * @return a list of change in the visible range. 
 */
public List<Change> getVisibleChanges(int firstVisibleIndex,int lastVisibleIndex){
  final List<Change> result=new ArrayList<>();
  for (int i=0, size=mChanges.size(); i < size; i++) {
    final Change change=mChanges.get(i);
    if (change.getIndex() > lastVisibleIndex || change.getIndex() + change.getCount() - 1 < firstVisibleIndex) {
      continue;
    }
    result.add(change);
  }
  return result;
}
