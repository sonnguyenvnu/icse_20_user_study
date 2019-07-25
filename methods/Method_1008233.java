/** 
 * a cluster state supersedes another state if they are from the same master and the version of this state is higher than that of the other state. <p> In essence that means that all the changes from the other cluster state are also reflected by the current one
 */
public boolean supersedes(ClusterState other){
  return this.version() > other.version();
}
