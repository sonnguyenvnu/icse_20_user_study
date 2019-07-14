/** 
 * Many parent traversals drip feed their start vertices in one at a time. To best exploit the multiQuery we need to load all possible starts in one go so this method will attempt to find a JanusGraphMultiQueryStep with the starts of the parent, and if found cache it.
 */
private void setParentMultiQueryStep(){
  Step firstStep=traversal.getStartStep();
  while (firstStep instanceof StartStep || firstStep instanceof SideEffectStep) {
    firstStep=firstStep.getNextStep();
  }
  if (this.equals(firstStep)) {
    Step<?,?> parentStep=traversal.getParent().asStep();
    if (JanusGraphTraversalUtil.isMultiQueryCompatibleStep(parentStep)) {
      Step<?,?> parentPreviousStep=parentStep.getPreviousStep();
      if (parentStep instanceof RepeatStep) {
        RepeatStep repeatStep=(RepeatStep)parentStep;
        List<RepeatEndStep> repeatEndSteps=TraversalHelper.getStepsOfClass(RepeatEndStep.class,repeatStep.getRepeatTraversal());
        if (repeatEndSteps.size() == 1) {
          parentPreviousStep=repeatEndSteps.get(0).getPreviousStep();
        }
      }
      if (parentPreviousStep instanceof ProfileStep) {
        parentPreviousStep=parentPreviousStep.getPreviousStep();
      }
      if (parentPreviousStep instanceof JanusGraphMultiQueryStep) {
        JanusGraphMultiQueryStep multiQueryStep=(JanusGraphMultiQueryStep)parentPreviousStep;
        parentMultiQueryStep=multiQueryStep;
      }
    }
  }
}
