/** 
 * Select feign contract methods <p> extract some code from  {@link Contract.BaseContract#parseAndValidatateMetadata(java.lang.Class)}
 * @param targetType
 * @return non-null
 */
private List<Method> selectFeignContractMethods(Class<?> targetType){
  List<Method> methods=new LinkedList<>();
  for (  Method method : targetType.getMethods()) {
    if (method.getDeclaringClass() == Object.class || (method.getModifiers() & Modifier.STATIC) != 0 || Util.isDefault(method)) {
      continue;
    }
    methods.add(method);
  }
  return methods;
}
