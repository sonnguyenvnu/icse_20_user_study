@Override protected void execute(){
  context.constructor.addStatement("this.writeBuffer = new $T<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX)",WRITE_QUEUE_TYPE);
  context.cache.addField(FieldSpec.builder(WRITE_QUEUE,"writeBuffer",Modifier.FINAL).build());
  context.cache.addMethod(MethodSpec.methodBuilder("writeBuffer").addModifiers(context.protectedFinalModifiers()).addStatement("return writeBuffer").returns(WRITE_QUEUE).build());
  context.cache.addMethod(MethodSpec.methodBuilder("buffersWrites").addModifiers(context.protectedFinalModifiers()).addStatement("return true").returns(boolean.class).build());
}
