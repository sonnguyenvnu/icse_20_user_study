/** 
 * Customize the given  {@link ProducesRequestCondition}. Default implementation returns the condition as is.
 * @param condition will never be {@literal null}.
 * @return
 */
protected ProducesRequestCondition customize(ProducesRequestCondition condition){
  return condition;
}
