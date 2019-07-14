private void addLink(String method,String varName){
  MethodSpec getter=MethodSpec.methodBuilder("get" + capitalize(method)).addModifiers(Modifier.PUBLIC).addStatement("return $N",varName).returns(NODE).build();
  MethodSpec setter=MethodSpec.methodBuilder("set" + capitalize(method)).addModifiers(Modifier.PUBLIC).addParameter(NODE,varName).addStatement("this.$N = $N",varName,varName).build();
  context.nodeSubtype.addMethod(getter).addMethod(setter);
}
