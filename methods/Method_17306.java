/** 
 * @return the set of Java native classes that are deeply copied. 
 */
public static Map<Class<?>,Function<Object,Object>> javaDeepCopyStrategies(){
  return JAVA_DEEP_COPY;
}
