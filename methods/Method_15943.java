/** 
 * @since 3.0.4
 */
public static <T>Query<T,QueryParamEntity> newQuery(){
  return Query.empty(new QueryParamEntity());
}
