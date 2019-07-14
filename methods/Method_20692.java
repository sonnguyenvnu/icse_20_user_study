/** 
 * Returns a (visibleItem, totalItemCount) pair given a linear layout manager.
 */
private @NonNull Pair<Integer,Integer> displayedItemFromLinearLayout(final @NonNull LinearLayoutManager manager){
  return new Pair<>(manager.findLastVisibleItemPosition(),manager.getItemCount());
}
