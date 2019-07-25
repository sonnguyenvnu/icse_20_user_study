/** 
 * Unboxes {expression} using the ***Value() method of the corresponding boxed type. e.g expression => expression.intValue().
 */
public static Expression unbox(Expression expression){
  DeclaredTypeDescriptor boxType=(DeclaredTypeDescriptor)expression.getTypeDescriptor().toRawTypeDescriptor();
  checkArgument(TypeDescriptors.isBoxedType(boxType));
  PrimitiveTypeDescriptor primitiveType=boxType.toUnboxedType();
  MethodDescriptor valueMethodDescriptor=boxType.getMethodDescriptorByName(primitiveType.getSimpleSourceName() + MethodDescriptor.VALUE_METHOD_SUFFIX);
  MethodCall methodCall=MethodCall.Builder.from(valueMethodDescriptor).setQualifier(expression.parenthesize()).build();
  if (TypeDescriptors.isBoxedBooleanOrDouble(boxType)) {
    methodCall=devirtualizeMethodCall(methodCall,boxType);
  }
  return methodCall;
}
