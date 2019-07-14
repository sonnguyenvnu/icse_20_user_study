@Override protected void execute(){
  context.constructor.addStatement("this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos()");
  context.cache.addField(FieldSpec.builder(long.class,"refreshAfterWriteNanos").addModifiers(Modifier.VOLATILE).build());
  context.cache.addMethod(MethodSpec.methodBuilder("refreshAfterWrite").addModifiers(context.protectedFinalModifiers()).addStatement("return true").returns(boolean.class).build());
  context.cache.addMethod(MethodSpec.methodBuilder("refreshAfterWriteNanos").addModifiers(context.protectedFinalModifiers()).addStatement("return refreshAfterWriteNanos").returns(long.class).build());
  context.cache.addMethod(MethodSpec.methodBuilder("setRefreshAfterWriteNanos").addStatement("this.refreshAfterWriteNanos = refreshAfterWriteNanos").addParameter(long.class,"refreshAfterWriteNanos").addModifiers(context.protectedFinalModifiers()).build());
}
