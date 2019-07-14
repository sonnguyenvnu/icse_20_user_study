/** 
 * This method can be used to recalculate the position of the item at the given index, without triggering an  {@link Callback#onChanged(int,int)} callback.<p> If you are editing objects in the list such that their position in the list may change but you don't want to trigger an onChange animation, you can use this method to re-position it. If the item changes position, SortedList will call  {@link Callback#onMoved(int,int)}without calling  {@link Callback#onChanged(int,int)}. <p> A sample usage may look like: <pre> final int position = mSortedList.indexOf(item); item.incrementPriority(); // assume items are sorted by priority mSortedList.recalculatePositionOfItemAt(position); </pre> In the example above, because the sorting criteria of the item has been changed, mSortedList.indexOf(item) will not be able to find the item. This is why the code above first gets the position before editing the item, edits it and informs the SortedList that item should be repositioned.
 * @param index The current index of the Item whose position should be re-calculated.
 * @see #updateItemAt(int,Object)
 * @see #add(Object)
 */
public void recalculatePositionOfItemAt(int index){
  throwIfInMutationOperation();
  final T item=get(index);
  removeItemAtIndex(index,false);
  int newIndex=add(item,false);
  if (index != newIndex) {
    mCallback.onMoved(index,newIndex);
  }
}
