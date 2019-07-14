/** 
 * Get the pointer of a centroid vector.
 * @return the pointer of a centroid vector
 */
SparseVector centroid_vector(){
  if (documents_.size() > 0 && composite_.size() == 0)   set_composite_vector();
  centroid_=(SparseVector)composite_vector().clone();
  centroid_.normalize();
  return centroid_;
}
