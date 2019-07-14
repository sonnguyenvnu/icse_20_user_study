@Override protected void execute(){
  context.constructor.addStatement("this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos()");
  context.cache.addField(FieldSpec.builder(long.class,"expiresAfterWriteNanos").addModifiers(Modifier.VOLATILE).build());
  context.cache.addMethod(MethodSpec.methodBuilder("expiresAfterWrite").addModifiers(context.protectedFinalModifiers()).addStatement("return true").returns(boolean.class).build());
  context.cache.addMethod(MethodSpec.methodBuilder("expiresAfterWriteNanos").addModifiers(context.protectedFinalModifiers()).addStatement("return expiresAfterWriteNanos").returns(long.class).build());
  context.cache.addMethod(MethodSpec.methodBuilder("setExpiresAfterWriteNanos").addStatement("this.expiresAfterWriteNanos = expiresAfterWriteNanos").addParameter(long.class,"expiresAfterWriteNanos").addModifiers(context.protectedFinalModifiers()).build());
}
