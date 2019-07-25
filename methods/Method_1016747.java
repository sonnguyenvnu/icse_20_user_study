/** 
 * While not converged, calls <code>improveClustering</code> to modify the current predicted  {@link Clustering}.
 * @param instances
 * @return The predicted {@link Clustering}.
 */
public Clustering cluster(InstanceList instances){
  return clusterKBest(instances,1)[0];
}
