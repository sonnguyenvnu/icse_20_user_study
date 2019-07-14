private void addEvicts(){
  context.cache.addMethod(MethodSpec.methodBuilder("evicts").addModifiers(context.protectedFinalModifiers()).addStatement("return true").returns(boolean.class).build());
}
