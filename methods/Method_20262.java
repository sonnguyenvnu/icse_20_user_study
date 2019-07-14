/** 
 * @param recyclerView the view.
 * @return the tracker for the given {@link RecyclerView}. Null if no tracker was attached.
 */
@Nullable private static EpoxyVisibilityTracker getTracker(@NonNull RecyclerView recyclerView){
  return (EpoxyVisibilityTracker)recyclerView.getTag(TAG_ID);
}
