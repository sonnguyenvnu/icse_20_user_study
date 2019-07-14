/** 
 * Upon the open managementsystem's commit, this graph will be asynchronously evicted from the cache on all JanusGraph nodes in your cluster, once there are no open transactions on this graph on each respective JanusGraph node and assuming each node is correctly configured to use the  {@link org.janusgraph.graphdb.management.JanusGraphManager}.
 */
public void evictGraphFromCache(){
  this.evictGraphFromCache=true;
  setUpdateTrigger(new GraphCacheEvictionCompleteTrigger(this.graph.getGraphName()));
}
