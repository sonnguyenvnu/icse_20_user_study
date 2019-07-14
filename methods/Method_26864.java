private boolean parentMatches(@NonNull View startParent,View endParent){
  boolean parentMatches=true;
  if (mReparent) {
    TransitionValues endValues=getMatchedTransitionValues(startParent,true);
    if (endValues == null) {
      parentMatches=startParent == endParent;
    }
 else {
      parentMatches=endParent == endValues.view;
    }
  }
  return parentMatches;
}
