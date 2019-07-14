private void addWeakValues(){
  context.nodeSubtype.addMethod(MethodSpec.methodBuilder("weakValues").addModifiers(Modifier.PUBLIC).addStatement("return true").returns(boolean.class).build());
}
