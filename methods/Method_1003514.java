/** 
 * Chain using  {@code AND}
 * @param criteria
 * @return
 */
public Criteria and(Criteria criteria){
  this.criteriaChain.add(criteria);
  return this;
}
