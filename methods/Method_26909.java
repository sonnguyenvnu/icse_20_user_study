/** 
 * Recursive method which captures values for an entire view hierarchy, starting at some root view. Transitions without targetIDs will use this method to capture values for all possible views.
 * @param view The view for which to capture values. Children of this Viewwill also be captured, recursively down to the leaf nodes.
 * @param start true if values are being captured in the start scene, falseotherwise.
 */
private void captureHierarchy(@Nullable View view,boolean start){
  if (view == null) {
    return;
  }
  int id=view.getId();
  if (mTargetIdExcludes != null && mTargetIdExcludes.contains(id)) {
    return;
  }
  if (mTargetExcludes != null && mTargetExcludes.contains(view)) {
    return;
  }
  if (mTargetTypeExcludes != null) {
    int numTypes=mTargetTypeExcludes.size();
    for (int i=0; i < numTypes; ++i) {
      if (mTargetTypeExcludes.get(i).isInstance(view)) {
        return;
      }
    }
  }
  if (view.getParent() instanceof ViewGroup) {
    TransitionValues values=new TransitionValues(view);
    if (start) {
      captureStartValues(values);
    }
 else {
      captureEndValues(values);
    }
    values.targetedTransitions.add(this);
    capturePropagationValues(values);
    if (start) {
      addViewValues(mStartValues,view,values);
    }
 else {
      addViewValues(mEndValues,view,values);
    }
  }
  if (view instanceof ViewGroup) {
    if (mTargetIdChildExcludes != null && mTargetIdChildExcludes.contains(id)) {
      return;
    }
    if (mTargetChildExcludes != null && mTargetChildExcludes.contains(view)) {
      return;
    }
    if (mTargetTypeChildExcludes != null) {
      int numTypes=mTargetTypeChildExcludes.size();
      for (int i=0; i < numTypes; ++i) {
        if (mTargetTypeChildExcludes.get(i).isInstance(view)) {
          return;
        }
      }
    }
    ViewGroup parent=(ViewGroup)view;
    for (int i=0; i < parent.getChildCount(); ++i) {
      captureHierarchy(parent.getChildAt(i),start);
    }
  }
}
