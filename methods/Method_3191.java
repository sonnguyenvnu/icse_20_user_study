/** 
 * Normalize a vector.
 */
void normalize(){
  double nrm=norm();
  for (  Map.Entry<Integer,Double> d : entrySet()) {
    d.setValue(d.getValue() / nrm);
  }
}
