CheckpointState create(CheckpointIdentity step){
  cloneTransientToCheckpoint();
  ConsistentNodeIdentityHelper consistentNodeIdentity=new ConsistentNodeIdentityHelper(new SNodePresentationComparator());
  consistentNodeIdentity.apply(myCheckpointModel);
  new ModelDependencyUpdate(myCheckpointModel).updateImportedModels(null);
  CheckpointIdentity prevCheckpoint=myTransitionTrace.getMostRecentCheckpoint();
  myTransitionTrace.newTransition(step,myTransientModel,consistentNodeIdentity.getChangedNodes());
  return new CheckpointState(myCheckpointModel,prevCheckpoint,step);
}
