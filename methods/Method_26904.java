/** 
 * Match start/end values by Adapter view ID. Adds matched values to mStartValuesList and mEndValuesList and removes them from unmatchedStart and unmatchedEnd, using startIds and endIds as a guide for which Views have unique IDs.
 */
private void matchIds(@NonNull ArrayMap<View,TransitionValues> unmatchedStart,@NonNull ArrayMap<View,TransitionValues> unmatchedEnd,@NonNull SparseArray<View> startIds,@NonNull SparseArray<View> endIds){
  int numStartIds=startIds.size();
  for (int i=0; i < numStartIds; i++) {
    View startView=startIds.valueAt(i);
    if (startView != null && isValidTarget(startView)) {
      View endView=endIds.get(startIds.keyAt(i));
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
