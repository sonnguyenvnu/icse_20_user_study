/** 
 * Serializes the method signature onto the specified buffer.
 * @param method Method
 * @param sb     StringBuilder
 */
private static void asStringOn(Method method,StringBuilder sb){
  Class<?> clazz=method.getDeclaringClass();
  sb.append(shortestNameFor(clazz));
  sb.append(CLASS_METHOD_DELIMITER);
  sb.append(method.getName());
  sb.append(METHOD_GROUP_DELIMITERS[0]);
  Class<?>[] argTypes=method.getParameterTypes();
  if (argTypes.length == 0) {
    sb.append(METHOD_GROUP_DELIMITERS[1]);
    return;
  }
  serializedTypeIdOn(argTypes[0],sb);
  for (int i=1; i < argTypes.length; i++) {
    sb.append(METHOD_ARG_DELIMITER);
    serializedTypeIdOn(argTypes[i],sb);
  }
  sb.append(METHOD_GROUP_DELIMITERS[1]);
}
