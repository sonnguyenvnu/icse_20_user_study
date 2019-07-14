/** 
 * Match start/end values by Adapter transitionName. Adds matched values to mStartValuesList and mEndValuesList and removes them from unmatchedStart and unmatchedEnd, using startNames and endNames as a guide for which Views have unique transitionNames.
 */
private void matchNames(@NonNull ArrayMap<View,TransitionValues> unmatchedStart,@NonNull ArrayMap<View,TransitionValues> unmatchedEnd,@NonNull ArrayMap<String,View> startNames,@NonNull ArrayMap<String,View> endNames){
  int numStartNames=startNames.size();
  for (int i=0; i < numStartNames; i++) {
    View startView=startNames.valueAt(i);
    if (startView != null && isValidTarget(startView)) {
      View endView=endNames.get(startNames.keyAt(i));
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
