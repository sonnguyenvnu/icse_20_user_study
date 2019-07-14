/** 
 * Sets max proxy count.
 * @param repeatedReferLimit the max proxy count
 * @return the max proxy count
 */
public ConsumerConfig<T> setRepeatedReferLimit(int repeatedReferLimit){
  this.repeatedReferLimit=repeatedReferLimit;
  return this;
}
