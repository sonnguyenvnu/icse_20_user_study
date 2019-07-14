/** 
 * Gets the view for the next element that we should render. Also updates current item index to the next item, based on  {@link #mItemDirection}
 * @return The next element that we should render.
 */
View next(RecyclerView.Recycler recycler){
  final View view=recycler.getViewForPosition(mCurrentPosition);
  mCurrentPosition+=mItemDirection;
  return view;
}
