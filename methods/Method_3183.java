/** 
 * k-means??
 * @param nclusters ????
 * @return ???????Set??????
 */
public List<Set<K>> kmeans(int nclusters){
  Cluster<K> cluster=new Cluster<K>();
  for (  Document<K> document : documents_.values()) {
    cluster.add_document(document);
  }
  cluster.section(nclusters);
  refine_clusters(cluster.sectioned_clusters());
  List<Cluster<K>> clusters_=new ArrayList<Cluster<K>>(nclusters);
  for (  Cluster<K> s : cluster.sectioned_clusters()) {
    s.refresh();
    clusters_.add(s);
  }
  return toResult(clusters_);
}
