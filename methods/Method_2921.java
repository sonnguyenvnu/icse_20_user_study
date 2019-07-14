/** 
 * ????
 * @param deprels ????
 * @param id ????
 * @return ??
 */
int DEPREL(final List<Integer> deprels,int id){
  return ((id != -1) ? (deprels.get(id) + kDeprelInFeaturespace) : kNilDeprel);
}
