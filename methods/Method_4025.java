/** 
 * Attaches the  {@link SnapHelper} to the provided RecyclerView, by calling{@link RecyclerView#setOnFlingListener(RecyclerView.OnFlingListener)}. You can call this method with  {@code null} to detach it from the current RecyclerView.
 * @param recyclerView The RecyclerView instance to which you want to add this helper or{@code null} if you want to remove SnapHelper from the currentRecyclerView.
 * @throws IllegalArgumentException if there is already a {@link RecyclerView.OnFlingListener}attached to the provided  {@link RecyclerView}.
 */
public void attachToRecyclerView(@Nullable RecyclerView recyclerView) throws IllegalStateException {
  if (mRecyclerView == recyclerView) {
    return;
  }
  if (mRecyclerView != null) {
    destroyCallbacks();
  }
  mRecyclerView=recyclerView;
  if (mRecyclerView != null) {
    setupCallbacks();
    mGravityScroller=new Scroller(mRecyclerView.getContext(),new DecelerateInterpolator());
    snapToTargetExistingView();
  }
}
