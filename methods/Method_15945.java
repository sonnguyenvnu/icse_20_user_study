/** 
 * ?????????????????,?????Query??.??: <pre> entity.toNestQuery().and("userId",userId); </pre> <p> ????: name=? or type=? <p> ?????: (name=? or type=?) and userId=?
 * @see this#toNestQuery(Consumer)
 * @since 3.0.4
 */
public <T>Query<T,QueryParamEntity> toNestQuery(){
  return toNestQuery(null);
}