/** 
 * ????
 * @param postags ????
 * @param id ????
 * @return ??
 */
int POSTAG(final List<Integer> postags,int id){
  return ((id != -1) ? (postags.get(id) + kPostagInFeaturespace) : kNilPostag);
}
