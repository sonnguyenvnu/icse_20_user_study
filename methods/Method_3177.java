/** 
 * Set a gain when the cluster sectioned.
 */
void set_sectioned_gain(){
  double gain=0.0f;
  if (sectioned_gain_ == 0 && sectioned_clusters_.size() > 1) {
    for (    Cluster<K> cluster : sectioned_clusters_) {
      gain+=cluster.composite_vector().norm();
    }
    gain-=composite_.norm();
  }
  sectioned_gain_=gain;
}
