/** 
 * Forces a cast to class A for target (only if types differ) 
 */
public static MethodHandle cast(Class<?> classA,MethodHandle target){
  MethodType newType=MethodType.methodType(classA).unwrap();
  MethodType targetType=MethodType.methodType(target.type().returnType()).unwrap();
  if (newType.returnType() == targetType.returnType()) {
    return target;
  }
  if (newType.returnType() == boolean.class || targetType.returnType() == boolean.class) {
    throw new ClassCastException("Cannot cast " + targetType.returnType() + " to " + newType.returnType());
  }
  return MethodHandles.explicitCastArguments(target,target.type().changeReturnType(newType.returnType()));
}
