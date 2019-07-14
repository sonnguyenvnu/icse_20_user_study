/** 
 * ??????
 * @param candidate ???
 * @return ????
 */
public static Class<?> getTargetClass(Object candidate){
  return (isCglibProxyClass(candidate.getClass()) ? candidate.getClass().getSuperclass() : candidate.getClass());
}
