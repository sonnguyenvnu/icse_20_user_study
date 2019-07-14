private MethodSpec makeContainsValue(){
  MethodSpec.Builder containsValue=MethodSpec.methodBuilder("containsValue").addModifiers(context.publicFinalModifiers()).addParameter(Object.class,"value").returns(boolean.class);
  if (isStrongValues()) {
    containsValue.addStatement("return $T.equals(value, getValue())",Objects.class);
  }
 else {
    containsValue.addStatement("return getValue() == value");
  }
  return containsValue.build();
}
