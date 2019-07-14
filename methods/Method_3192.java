/** 
 * Calculate the inner product value between vectors.
 */
static double inner_product(SparseVector vec1,SparseVector vec2){
  Iterator<Map.Entry<Integer,Double>> it;
  SparseVector other;
  if (vec1.size() < vec2.size()) {
    it=vec1.entrySet().iterator();
    other=vec2;
  }
 else {
    it=vec2.entrySet().iterator();
    other=vec1;
  }
  double prod=0;
  while (it.hasNext()) {
    Map.Entry<Integer,Double> entry=it.next();
    prod+=entry.getValue() * other.get(entry.getKey());
  }
  return prod;
}
