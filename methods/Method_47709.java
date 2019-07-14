/** 
 * Selects or deselects the item at a given position.
 * @param position position of the item to be toggled
 */
public void toggleSelection(int position){
  Habit h=getItem(position);
  if (h == null)   return;
  int k=selected.indexOf(h);
  if (k < 0)   selected.add(h);
 else   selected.remove(h);
  notifyDataSetChanged();
}
