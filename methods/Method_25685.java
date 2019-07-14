private Description visitNewClassOrMethodInvocation(InvocationInfo invocationInfo){
  Changes changes=argumentchangeFinder.findChanges(invocationInfo);
  if (changes.isEmpty()) {
    return Description.NO_MATCH;
  }
  Description.Builder description=buildDescription(invocationInfo.tree()).setMessage(changes.describe(invocationInfo));
  description.addFix(changes.buildCommentArgumentsFix(invocationInfo));
  description.addFix(changes.buildPermuteArgumentsFix(invocationInfo));
  return description.build();
}
