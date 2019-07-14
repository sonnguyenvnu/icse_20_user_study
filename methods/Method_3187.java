/** 
 * repeated bisection ??
 * @param nclusters  ????
 * @param limit_eval ????????
 * @return ???????Set??????
 */
public List<Set<K>> repeatedBisection(int nclusters,double limit_eval){
  Cluster<K> cluster=new Cluster<K>();
  List<Cluster<K>> clusters_=new ArrayList<Cluster<K>>(nclusters > 0 ? nclusters : 16);
  for (  Document<K> document : documents_.values()) {
    cluster.add_document(document);
  }
  PriorityQueue<Cluster<K>> que=new PriorityQueue<Cluster<K>>();
  cluster.section(2);
  refine_clusters(cluster.sectioned_clusters());
  cluster.set_sectioned_gain();
  cluster.composite_vector().clear();
  que.add(cluster);
  while (!que.isEmpty()) {
    if (nclusters > 0 && que.size() >= nclusters)     break;
    cluster=que.peek();
    if (cluster.sectioned_clusters().size() < 1)     break;
    if (limit_eval > 0 && cluster.sectioned_gain() < limit_eval)     break;
    que.poll();
    List<Cluster<K>> sectioned=cluster.sectioned_clusters();
    for (    Cluster<K> c : sectioned) {
      c.section(2);
      refine_clusters(c.sectioned_clusters());
      c.set_sectioned_gain();
      if (c.sectioned_gain() < limit_eval) {
        for (        Cluster<K> sub : c.sectioned_clusters()) {
          sub.clear();
        }
      }
      c.composite_vector().clear();
      que.add(c);
    }
  }
  while (!que.isEmpty()) {
    clusters_.add(0,que.poll());
  }
  return toResult(clusters_);
}
