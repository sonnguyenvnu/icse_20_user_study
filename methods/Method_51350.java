@Override public String errorFor(T value){
  String typeError=typeErrorFor(value);
  if (typeError != null) {
    return typeError;
  }
  return valueErrorFor(value);
}
