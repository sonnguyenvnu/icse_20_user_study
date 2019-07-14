/** 
 * <p>Returns the amount of extra space that should be laid out by LayoutManager.</p> <p>By default,  {@link LinearLayoutManager} lays out 1 extra pageof items while smooth scrolling and 0 otherwise. You can override this method to implement your custom layout pre-cache logic.</p> <p><strong>Note:</strong>Laying out invisible elements generally comes with significant performance cost. It's typically only desirable in places like smooth scrolling to an unknown location, where 1) the extra content helps LinearLayoutManager know in advance when its target is approaching, so it can decelerate early and smoothly and 2) while motion is continuous.</p> <p>Extending the extra layout space is especially expensive if done while the user may change scrolling direction. Changing direction will cause the extra layout space to swap to the opposite side of the viewport, incurring many rebinds/recycles, unless the cache is large enough to handle it.</p>
 * @return The extra space that should be laid out (in pixels).
 * @deprecated  Use {@link #calculateExtraLayoutSpace(RecyclerView.State,int[])} instead.
 */
@SuppressWarnings("DeprecatedIsStillUsed") @Deprecated protected int getExtraLayoutSpace(RecyclerView.State state){
  if (state.hasTargetScrollPosition()) {
    return mOrientationHelper.getTotalSpace();
  }
 else {
    return 0;
  }
}
