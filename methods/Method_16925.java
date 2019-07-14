private void addSoftValues(){
  context.nodeSubtype.addMethod(MethodSpec.methodBuilder("softValues").addModifiers(Modifier.PUBLIC).addStatement("return true").returns(boolean.class).build());
}
