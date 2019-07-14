/** 
 * ??????bean
 * @param clazz
 * @return
 */
public static <T>T getBean(Class<T> clazz){
  T t=null;
  Map<String,T> map=context.getBeansOfType(clazz);
  for (  Map.Entry<String,T> entry : map.entrySet()) {
    t=entry.getValue();
  }
  return t;
}
