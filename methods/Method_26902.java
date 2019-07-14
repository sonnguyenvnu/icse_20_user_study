/** 
 * Match start/end values by View instance. Adds matched values to mStartValuesList and mEndValuesList and removes them from unmatchedStart and unmatchedEnd.
 */
private void matchInstances(@NonNull ArrayMap<View,TransitionValues> unmatchedStart,@NonNull ArrayMap<View,TransitionValues> unmatchedEnd){
  for (int i=unmatchedStart.size() - 1; i >= 0; i--) {
    View view=unmatchedStart.keyAt(i);
    if (view != null && isValidTarget(view)) {
      TransitionValues end=unmatchedEnd.remove(view);
      if (end != null && isValidTarget(end.view)) {
        TransitionValues start=unmatchedStart.removeAt(i);
        mStartValuesList.add(start);
        mEndValuesList.add(end);
      }
    }
  }
}
