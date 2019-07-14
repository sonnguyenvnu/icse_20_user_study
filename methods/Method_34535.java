/** 
 * Check whether return type of the given method is parametrized or not.
 * @param method the method
 * @return true - if return type is {@link ParameterizedType}, otherwise - false
 */
public static boolean isReturnTypeParametrized(Method method){
  return isParametrizedType(method.getGenericReturnType());
}
