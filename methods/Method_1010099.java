@Override public void search(Set<SAbstractConcept> elements,SearchScope scope,Consumer<SNode> consumer,@NotNull ProgressMonitor monitor){
  assert !elements.contains(null);
  Collection<FindUsagesParticipant> participants=PersistenceFacade.getInstance().getFindUsagesParticipants();
  monitor.start("Finding usages...",participants.size() + 5);
  try {
    Set<SAbstractConcept> queryConcepts=new HashSet<>(elements);
    if (!myExact) {
      for (      SAbstractConcept concept : elements) {
        queryConcepts.addAll(ConceptDescendantsCache.getInstance().getDescendants(concept));
      }
    }
    monitor.advance(1);
    Collection<SModel> current=new LinkedHashSet<>();
    Collection<SModel> simpleSearch=new LinkedHashSet<>();
    for (    SModel m : IterableUtil.asCollection(scope.getModels())) {
      if (m instanceof EditableSModel && ((EditableSModel)m).isChanged()) {
        simpleSearch.add(m);
      }
 else {
        current.add(m);
      }
    }
    for (    FindUsagesParticipant participant : participants) {
      final Set<SModel> next=new HashSet<>(current);
      participant.findInstances(current,queryConcepts,consumer,next::remove);
      current=next;
      monitor.advance(1);
    }
    ProgressMonitor subMonitor=monitor.subTask(4,SubProgressKind.DEFAULT);
    subMonitor.start("",current.size() + simpleSearch.size());
    showNoFastFindTipIfNeeded(current);
    current.addAll(simpleSearch);
    for (    SModel m : current) {
      subMonitor.step(m.getName().getSimpleName());
      FindUsagesUtil.collectInstances(m,queryConcepts,consumer,monitor);
      if (monitor.isCanceled()) {
        break;
      }
      subMonitor.advance(1);
    }
    subMonitor.done();
  }
  finally {
    monitor.done();
  }
}
