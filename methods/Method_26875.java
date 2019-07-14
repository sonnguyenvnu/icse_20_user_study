private boolean parentsMatch(@NonNull ViewGroup startParent,ViewGroup endParent){
  boolean parentsMatch=false;
  if (!isValidTarget(startParent) || !isValidTarget(endParent)) {
    parentsMatch=startParent == endParent;
  }
 else {
    TransitionValues endValues=getMatchedTransitionValues(startParent,true);
    if (endValues != null) {
      parentsMatch=endParent == endValues.view;
    }
  }
  return parentsMatch;
}
