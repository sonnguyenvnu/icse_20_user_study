/** 
 * Returns the descriptor corresponding to the given argument and return types.
 * @param returnType the return type of the method.
 * @param argumentTypes the argument types of the method.
 * @return the descriptor corresponding to the given argument and return types.
 */
public static String getMethodDescriptor(final Type returnType,final Type... argumentTypes){
  StringBuilder stringBuilder=new StringBuilder();
  stringBuilder.append('(');
  for (  Type argumentType : argumentTypes) {
    argumentType.appendDescriptor(stringBuilder);
  }
  stringBuilder.append(')');
  returnType.appendDescriptor(stringBuilder);
  return stringBuilder.toString();
}
