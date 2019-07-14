private Type handlerParameterizedType(ParameterizedType type,Type[] actualTypeArguments,int actualIndex){
  Class<?> thisClass=this.getClass();
  Type rawType=type.getRawType();
  Type[] argTypes=type.getActualTypeArguments();
  for (int i=0; i < argTypes.length; ++i) {
    if (argTypes[i] instanceof TypeVariable && actualIndex < actualTypeArguments.length) {
      argTypes[i]=actualTypeArguments[actualIndex++];
    }
    if (argTypes[i] instanceof GenericArrayType) {
      argTypes[i]=TypeUtils.checkPrimitiveArray((GenericArrayType)argTypes[i]);
    }
    if (argTypes[i] instanceof ParameterizedType) {
      return handlerParameterizedType((ParameterizedType)argTypes[i],actualTypeArguments,actualIndex);
    }
  }
  Type key=new ParameterizedTypeImpl(argTypes,thisClass,rawType);
  return key;
}
