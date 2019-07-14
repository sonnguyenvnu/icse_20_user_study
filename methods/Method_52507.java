/** 
 * @return An unparameterized MethodType
 */
public static MethodType build(Method method){
  return new MethodType(null,null,method);
}
