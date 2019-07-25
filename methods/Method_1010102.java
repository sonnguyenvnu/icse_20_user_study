@Override public void search(Set<SNode> nodes,@NotNull SearchScope scope,@NotNull Consumer<SReference> consumer,@NotNull ProgressMonitor monitor){
  Collection<FindUsagesParticipant> participants=PersistenceRegistry.getInstance().getFindUsagesParticipants();
  monitor.start("Finding usages...",participants.size() + 4);
  try {
    if (monitor.isCanceled()) {
      return;
    }
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
      if (monitor.isCanceled()) {
        return;
      }
      participant.findUsages(current,nodes,consumer,sModel -> {
        assert !(sModel instanceof EditableSModel && ((EditableSModel)sModel).isChanged());
        next.remove(sModel);
      }
,monitor);
      current=next;
      monitor.advance(1);
    }
    ProgressMonitor subMonitor=monitor.subTask(4,SubProgressKind.DEFAULT);
    subMonitor.start("",current.size() + simpleSearch.size());
    NodeUsageFinder nf=new NodeUsageFinder(nodes,consumer);
    showNoFastFindTipIfNeeded(current);
    current.addAll(simpleSearch);
    for (    SModel m : current) {
      subMonitor.step(m.getName().getSimpleName());
      nf.collectUsages(m,monitor);
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
