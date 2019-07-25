List<GenerationPhase> solve(){
  myPriorityGraph.finalizeEdges(myConfigurations);
  myPriorityGraph.checkSelfLocking(myConflicts);
  myPriorityGraph.checkLowPrioLocksTopPrio(myConflicts);
  final Collection<Group> cycles=myPriorityGraph.removeWeakCycles();
  mySameStepGroups.addAll(cycles);
  List<Group> coherentMappings=joinSameStepMappings();
  myPriorityGraph.updateWithCoherent(coherentMappings,myConflicts);
  myPriorityGraph.replaceWeakEdgesWithStrict();
  boolean topPriorityGroup=false;
  ArrayDeque<Collection<Group>> stack=new ArrayDeque<>();
  while (!myPriorityGraph.isEmpty()) {
    Collection<Group> step=myPriorityGraph.getGroupsNotInDependency();
    if (step.isEmpty()) {
      myPriorityGraph.reportEdgesLeft(myConflicts);
      break;
    }
    for (Iterator<Group> it=step.iterator(); it.hasNext(); ) {
      if (it.next().isTopPriority() != topPriorityGroup) {
        it.remove();
      }
    }
    if (step.isEmpty()) {
      if (topPriorityGroup) {
        myPriorityGraph.reportEdgesLeft(myConflicts);
        break;
      }
      topPriorityGroup=true;
    }
 else {
      stack.push(step);
      myPriorityGraph.dropEdgesOf(step);
    }
  }
  ArrayList<GenerationPhase> rv=new ArrayList<>(stack.size());
  while (!stack.isEmpty()) {
    rv.add(new GenerationPhase(stack.pop()));
  }
  return rv;
}
