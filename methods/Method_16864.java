private void fixedExpiration(){
  context.constructor.addStatement("this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos()");
  context.cache.addField(FieldSpec.builder(long.class,"expiresAfterAccessNanos").addModifiers(Modifier.VOLATILE).build());
  context.cache.addMethod(MethodSpec.methodBuilder("expiresAfterAccess").addModifiers(context.protectedFinalModifiers()).addStatement("return (timerWheel == null)").returns(boolean.class).build());
  context.cache.addMethod(MethodSpec.methodBuilder("expiresAfterAccessNanos").addModifiers(context.protectedFinalModifiers()).addStatement("return expiresAfterAccessNanos").returns(long.class).build());
  context.cache.addMethod(MethodSpec.methodBuilder("setExpiresAfterAccessNanos").addStatement("this.expiresAfterAccessNanos = expiresAfterAccessNanos").addParameter(long.class,"expiresAfterAccessNanos").addModifiers(context.protectedFinalModifiers()).build());
}
