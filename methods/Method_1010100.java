@Override public void search(Set<SModelReference> models,SearchScope scope,Consumer<SModel> consumer,@NotNull ProgressMonitor monitor){
  Collection<FindUsagesParticipant> participants=PersistenceRegistry.getInstance().getFindUsagesParticipants();
  monitor.start("Finding model(s) usages...",participants.size() + 4);
  try {
    Collection<SModel> current=IterableUtil.asCollection(scope.getModels());
    for (    FindUsagesParticipant participant : participants) {
      final Set<SModel> next=new HashSet<>(current);
      participant.findModelUsages(current,models,consumer,next::remove);
      current=next;
      monitor.advance(1);
    }
    ProgressMonitor subMonitor=monitor.subTask(4,SubProgressKind.DEFAULT);
    subMonitor.start("",current.size());
    for (    SModel m : current) {
      subMonitor.step(m.getName().getSimpleName());
      if (FindUsagesUtil.hasModelUsages(m,models)) {
        consumer.consume(m);
      }
      if (monitor.isCanceled())       break;
      subMonitor.advance(1);
    }
    subMonitor.done();
  }
  finally {
    monitor.done();
  }
}
