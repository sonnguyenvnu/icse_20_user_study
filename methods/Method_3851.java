/** 
 * Among the children that are suitable to be considered as an anchor child, returns the one closest to the end of the layout. <p> Due to ambiguous adapter updates or children being removed, some children's positions may be invalid. This method is a best effort to find a position within adapter bounds if possible. <p> It also prioritizes children that are within the visible bounds.
 * @return A View that can be used an an anchor View.
 */
private View findReferenceChildClosestToEnd(RecyclerView.Recycler recycler,RecyclerView.State state){
  return mShouldReverseLayout ? findFirstReferenceChild(recycler,state) : findLastReferenceChild(recycler,state);
}
