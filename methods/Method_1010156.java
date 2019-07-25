/** 
 * @return recorded snapshot of model transition states so that we can use new object for another plan branch
 */
public ModelTransitions fork(){
  if (myActiveTransition == null) {
    throw new IllegalStateException("How come I need to fork when there's no ongoing transformation?");
  }
  ModelTransitions rv=new ModelTransitions();
  rv.myCheckpoints.addAll(myCheckpoints);
  CheckpointIdentity recentCheckpoint=getMostRecentCheckpoint();
  rv.myActiveTransition=recentCheckpoint == null ? new TransitionTrace(rv) : new TransitionTrace(recentCheckpoint,rv);
  return rv;
}
