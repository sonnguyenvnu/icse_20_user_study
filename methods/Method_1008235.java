/** 
 * Build a cluster state update task configuration with the specified  {@link Priority} and timeout.
 * @param priority the priority for the associated cluster stateupdate task
 * @param timeout  the timeout for the associated cluster stateupdate task
 * @return the result cluster state update task configuration
 */
static ClusterStateTaskConfig build(Priority priority,TimeValue timeout){
  return new Basic(priority,timeout);
}
