/** 
 * This method searches the traversal for traversal parents which are multiQuery compatible. Being multiQuery compatible is not solely determined by the class of the parent step, it must also have a vertex step as the first step in one of its local or global children.
 * @param traversal The traversal in which to search for multiQuery compatible steps
 * @return A list of traversal parents which were multiQuery compatible
 */
public static List<Step> getMultiQueryCompatibleSteps(final Traversal.Admin<?,?> traversal){
  final Set<Step> multiQueryCompatibleSteps=new HashSet<>();
  for (  final Step step : traversal.getSteps()) {
    if (isMultiQueryCompatibleStep(step)) {
      Step parentStep=step;
      ((TraversalParent)parentStep).getGlobalChildren().forEach(childTraversal -> getMultiQueryCompatibleStepsFromChildTraversal(childTraversal,parentStep,multiQueryCompatibleSteps));
      ((TraversalParent)parentStep).getLocalChildren().forEach(childTraversal -> getMultiQueryCompatibleStepsFromChildTraversal(childTraversal,parentStep,multiQueryCompatibleSteps));
      if (parentStep instanceof RepeatStep && multiQueryCompatibleSteps.contains(parentStep)) {
        RepeatStep repeatStep=(RepeatStep)parentStep;
        List<RepeatEndStep> repeatEndSteps=TraversalHelper.getStepsOfClass(RepeatEndStep.class,repeatStep.getRepeatTraversal());
        if (repeatEndSteps.size() == 1) {
          multiQueryCompatibleSteps.remove(parentStep);
          multiQueryCompatibleSteps.add(repeatEndSteps.get(0));
        }
      }
    }
  }
  return Lists.newArrayList(multiQueryCompatibleSteps);
}
