private Class<?> getElementTypeRec(Class<?> arrayType){
  return arrayType.isArray() ? getElementTypeRec(arrayType.getComponentType()) : arrayType;
}
