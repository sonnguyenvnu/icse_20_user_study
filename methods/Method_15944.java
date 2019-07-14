/** 
 * @since 3.0.4
 */
public <T>Query<T,QueryParamEntity> toQuery(){
  return Query.empty(this);
}
