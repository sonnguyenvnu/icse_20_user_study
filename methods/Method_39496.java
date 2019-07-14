@Override public void visitBaseType(final char descriptor){
  String baseType=BASE_TYPES.get(descriptor);
  if (baseType == null) {
    throw new IllegalArgumentException();
  }
  declaration.append(baseType);
  endType();
}
