/** 
 * Find all removal operations and add them to the result list. The general strategy here is to walk through the  {@link #oldStateList} and check for items that don't exist in the new list.Walking through it in order makes it easy to batch adjacent removals.
 */
private void collectRemovals(UpdateOpHelper helper){
  for (  ModelState state : oldStateList) {
    state.position-=helper.getNumRemovals();
    state.pair=currentStateMap.get(state.id);
    if (state.pair != null) {
      state.pair.pair=state;
      continue;
    }
    helper.remove(state.position);
  }
}
