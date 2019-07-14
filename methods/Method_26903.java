/** 
 * Match start/end values by Adapter item ID. Adds matched values to mStartValuesList and mEndValuesList and removes them from unmatchedStart and unmatchedEnd, using startItemIds and endItemIds as a guide for which Views have unique item IDs.
 */
private void matchItemIds(@NonNull ArrayMap<View,TransitionValues> unmatchedStart,@NonNull ArrayMap<View,TransitionValues> unmatchedEnd,@NonNull LongSparseArray<View> startItemIds,@NonNull LongSparseArray<View> endItemIds){
  int numStartIds=startItemIds.size();
  for (int i=0; i < numStartIds; i++) {
    View startView=startItemIds.valueAt(i);
    if (startView != null && isValidTarget(startView)) {
      View endView=endItemIds.get(startItemIds.keyAt(i));
      if (endView != null && isValidTarget(endView)) {
        TransitionValues startValues=unmatchedStart.get(startView);
        TransitionValues endValues=unmatchedEnd.get(endView);
        if (startValues != null && endValues != null) {
          mStartValuesList.add(startValues);
          mEndValuesList.add(endValues);
          unmatchedStart.remove(startView);
          unmatchedEnd.remove(endView);
        }
      }
    }
  }
}
