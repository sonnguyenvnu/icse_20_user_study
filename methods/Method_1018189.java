@Override public AlertCriteria transfer(AlertCriteria criteria){
  AlertCriteria c=super.transfer(criteria);
  AtomicReference<KyloEntityAwareAlertCriteria> updated=new AtomicReference<>((KyloEntityAwareAlertCriteria)c);
  this.entityCriteria.forEach((t) -> updated.set(updated.get().entityCriteria(t)));
  return updated.get();
}
