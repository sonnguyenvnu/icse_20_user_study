/** 
 * ???????
 * @param type POJO ??
 * @param mathcer ?????
 * @return ?????
 */
public static FieldFilter create(Class<?> type,FieldMatcher mathcer){
  FieldFilter ff=new FieldFilter();
  ff.set(type,mathcer);
  return ff;
}
