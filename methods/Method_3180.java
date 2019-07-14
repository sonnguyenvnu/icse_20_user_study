/** 
 * ??????nclusters??
 * @param nclusters
 */
void section(int nclusters){
  if (size() < nclusters)   return;
  sectioned_clusters_=new ArrayList<Cluster<K>>(nclusters);
  List<Document> centroids=new ArrayList<Document>(nclusters);
  choose_smartly(nclusters,centroids);
  for (int i=0; i < centroids.size(); i++) {
    Cluster<K> cluster=new Cluster<K>();
    sectioned_clusters_.add(cluster);
  }
  for (  Document<K> d : documents_) {
    double max_similarity=-1.0;
    int max_index=0;
    for (int j=0; j < centroids.size(); j++) {
      double similarity=SparseVector.inner_product(d.feature(),centroids.get(j).feature());
      if (max_similarity < similarity) {
        max_similarity=similarity;
        max_index=j;
      }
    }
    sectioned_clusters_.get(max_index).add_document(d);
  }
}
