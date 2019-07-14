/** 
 * Resolves <code>TypeVariable</code> with given implementation class.
 */
public static Type resolveVariable(final TypeVariable variable,final Class implClass){
  final Class rawType=getRawType(implClass,null);
  int index=ArraysUtil.indexOf(rawType.getTypeParameters(),variable);
  if (index >= 0) {
    return variable;
  }
  final Class[] interfaces=rawType.getInterfaces();
  final Type[] genericInterfaces=rawType.getGenericInterfaces();
  for (int i=0; i <= interfaces.length; i++) {
    Class rawInterface;
    if (i < interfaces.length) {
      rawInterface=interfaces[i];
    }
 else {
      rawInterface=rawType.getSuperclass();
      if (rawInterface == null) {
        continue;
      }
    }
    final Type resolved=resolveVariable(variable,rawInterface);
    if (resolved instanceof Class || resolved instanceof ParameterizedType) {
      return resolved;
    }
    if (resolved instanceof TypeVariable) {
      final TypeVariable typeVariable=(TypeVariable)resolved;
      index=ArraysUtil.indexOf(rawInterface.getTypeParameters(),typeVariable);
      if (index < 0) {
        throw new IllegalArgumentException("Invalid type variable:" + typeVariable);
      }
      final Type type=i < genericInterfaces.length ? genericInterfaces[i] : rawType.getGenericSuperclass();
      if (type instanceof Class) {
        return Object.class;
      }
      if (type instanceof ParameterizedType) {
        return ((ParameterizedType)type).getActualTypeArguments()[index];
      }
      throw new IllegalArgumentException("Unsupported type: " + type);
    }
  }
  return null;
}
