/** 
 * Chain using  {@code AND}
 * @param criterias
 * @return
 */
public Criteria and(Criteria... criterias){
  this.criteriaChain.addAll(Arrays.asList(criterias));
  return this;
}
