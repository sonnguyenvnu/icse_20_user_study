/** 
 * no changes were made to the cluster state. Useful to execute a runnable on the cluster state applier thread
 */
public static ClusterTasksResult<LocalClusterUpdateTask> unchanged(){
  return new ClusterTasksResult<>(null,null);
}
