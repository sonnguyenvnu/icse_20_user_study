/** 
 * ??Abc[]???????
 */
public static Type array(Class<?> clazz){
  return new NutType(Array.newInstance(clazz,0).getClass());
}
