private static Type getInheritGenericType(Class<?> clazz,Type type,TypeVariable<?> tv){
  GenericDeclaration gd=tv.getGenericDeclaration();
  Class<?> class_gd=null;
  if (gd instanceof Class) {
    class_gd=(Class<?>)tv.getGenericDeclaration();
  }
  Type[] arguments=null;
  if (class_gd == clazz) {
    if (type instanceof ParameterizedType) {
      ParameterizedType ptype=(ParameterizedType)type;
      arguments=ptype.getActualTypeArguments();
    }
  }
 else {
    for (Class<?> c=clazz; c != null && c != Object.class && c != class_gd; c=c.getSuperclass()) {
      Type superType=c.getGenericSuperclass();
      if (superType instanceof ParameterizedType) {
        ParameterizedType p_superType=(ParameterizedType)superType;
        Type[] p_superType_args=p_superType.getActualTypeArguments();
        getArgument(p_superType_args,c.getTypeParameters(),arguments);
        arguments=p_superType_args;
      }
    }
  }
  if (arguments == null || class_gd == null) {
    return null;
  }
  Type actualType=null;
  TypeVariable<?>[] typeVariables=class_gd.getTypeParameters();
  for (int j=0; j < typeVariables.length; ++j) {
    if (tv.equals(typeVariables[j])) {
      actualType=arguments[j];
      break;
    }
  }
  return actualType;
}
