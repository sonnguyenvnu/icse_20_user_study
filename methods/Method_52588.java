public boolean isLeftProper(){
  return leftProperType != null && !leftProperType.isNullType() && !leftProperType.isPrimitive() && !leftProperType.isArrayType() && !leftProperType.isGeneric();
}
