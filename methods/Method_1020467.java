/** 
 * Boxes  {@code expression} using the valueOf() method of the corresponding boxed type. e.g.expression => Integer.valueOf(expression).
 */
public static Expression box(Expression expression){
  PrimitiveTypeDescriptor primitiveType=(PrimitiveTypeDescriptor)expression.getTypeDescriptor();
  checkArgument(!TypeDescriptors.isPrimitiveVoid(primitiveType));
  checkArgument(!TypeDescriptors.isPrimitiveBooleanOrDouble(primitiveType));
  DeclaredTypeDescriptor boxType=primitiveType.toBoxedType();
  MethodDescriptor valueOfMethodDescriptor=boxType.getMethodDescriptorByName(MethodDescriptor.VALUE_OF_METHOD_NAME,primitiveType);
  return MethodCall.Builder.from(valueOfMethodDescriptor).setArguments(expression).build();
}
