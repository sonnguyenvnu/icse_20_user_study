public static Type getFieldType(final Class<?> clazz,final Type type,Type fieldType){
  if (clazz == null || type == null) {
    return fieldType;
  }
  if (fieldType instanceof GenericArrayType) {
    GenericArrayType genericArrayType=(GenericArrayType)fieldType;
    Type componentType=genericArrayType.getGenericComponentType();
    Type componentTypeX=getFieldType(clazz,type,componentType);
    if (componentType != componentTypeX) {
      Type fieldTypeX=Array.newInstance(TypeUtils.getClass(componentTypeX),0).getClass();
      return fieldTypeX;
    }
    return fieldType;
  }
  if (!TypeUtils.isGenericParamType(type)) {
    return fieldType;
  }
  if (fieldType instanceof TypeVariable) {
    ParameterizedType paramType=(ParameterizedType)TypeUtils.getGenericParamType(type);
    Class<?> parameterizedClass=TypeUtils.getClass(paramType);
    final TypeVariable<?> typeVar=(TypeVariable<?>)fieldType;
    TypeVariable<?>[] typeVariables=parameterizedClass.getTypeParameters();
    for (int i=0; i < typeVariables.length; ++i) {
      if (typeVariables[i].getName().equals(typeVar.getName())) {
        fieldType=paramType.getActualTypeArguments()[i];
        return fieldType;
      }
    }
  }
  if (fieldType instanceof ParameterizedType) {
    ParameterizedType parameterizedFieldType=(ParameterizedType)fieldType;
    Type[] arguments=parameterizedFieldType.getActualTypeArguments();
    TypeVariable<?>[] typeVariables;
    ParameterizedType paramType;
    if (type instanceof ParameterizedType) {
      paramType=(ParameterizedType)type;
      typeVariables=clazz.getTypeParameters();
    }
 else     if (clazz.getGenericSuperclass() instanceof ParameterizedType) {
      paramType=(ParameterizedType)clazz.getGenericSuperclass();
      typeVariables=clazz.getSuperclass().getTypeParameters();
    }
 else {
      paramType=parameterizedFieldType;
      typeVariables=type.getClass().getTypeParameters();
    }
    boolean changed=getArgument(arguments,typeVariables,paramType.getActualTypeArguments());
    if (changed) {
      fieldType=new ParameterizedTypeImpl(arguments,parameterizedFieldType.getOwnerType(),parameterizedFieldType.getRawType());
      return fieldType;
    }
  }
  return fieldType;
}
