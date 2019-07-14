/** 
 * Internal utility method for checking whether a given view/id is valid for this transition, where "valid" means that either the Transition has no target/targetId list (the default, in which cause the transition should act on all views in the hiearchy), or the given view is in the target list or the view id is in the targetId list. If the target parameter is null, then the target list is not checked (this is in the case of ListView items, where the views are ignored and only the ids are used).
 */
boolean isValidTarget(@Nullable View target){
  if (target == null) {
    return false;
  }
  int targetId=target.getId();
  if (mTargetIdExcludes != null && mTargetIdExcludes.contains(targetId)) {
    return false;
  }
  if (mTargetExcludes != null && mTargetExcludes.contains(target)) {
    return false;
  }
  if (mTargetTypeExcludes != null) {
    int numTypes=mTargetTypeExcludes.size();
    for (int i=0; i < numTypes; ++i) {
      Class type=mTargetTypeExcludes.get(i);
      if (type.isInstance(target)) {
        return false;
      }
    }
  }
  final String transitionName=ViewUtils.getTransitionName(target);
  if (mTargetNameExcludes != null && transitionName != null) {
    if (mTargetNameExcludes.contains(transitionName)) {
      return false;
    }
  }
  if (mTargetIds.size() == 0 && mTargets.size() == 0 && (mTargetTypes == null || mTargetTypes.isEmpty()) && (mTargetNames == null || mTargetNames.isEmpty())) {
    return true;
  }
  if (mTargetIds.contains(targetId) || mTargets.contains(target)) {
    return true;
  }
  if (mTargetNames != null && mTargetNames.contains(transitionName)) {
    return true;
  }
  if (mTargetTypes != null) {
    for (int i=0; i < mTargetTypes.size(); ++i) {
      if (mTargetTypes.get(i).isInstance(target)) {
        return true;
      }
    }
  }
  return false;
}
