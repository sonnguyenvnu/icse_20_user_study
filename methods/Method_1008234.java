/** 
 * Build a cluster state update task configuration with the specified  {@link Priority} and no timeout.
 * @param priority the priority for the associated cluster stateupdate task
 * @return the resulting cluster state update task configuration
 */
static ClusterStateTaskConfig build(Priority priority){
  return new Basic(priority,null);
}
