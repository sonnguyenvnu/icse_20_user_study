/** 
 * While not converged, call <code>improveClustering</code> to modify the current predicted  {@link Clustering}.
 * @param instances Instances to cluster.
 * @param iterations Maximum number of iterations.
 * @param initialClustering Initial clustering of the Instances.
 * @return The predicted {@link Clustering}
 */
public Clustering cluster(InstanceList instances,int iterations,Clustering initialClustering){
  return clusterKBest(instances,iterations,initialClustering,1)[0];
}
