private void variableExpiration(){
  context.cache.addMethod(MethodSpec.methodBuilder("expiresVariable").addModifiers(context.protectedFinalModifiers()).addStatement("return (timerWheel != null)").returns(boolean.class).build());
  context.constructor.addStatement("this.expiry = builder.getExpiry(isAsync)");
  context.cache.addField(FieldSpec.builder(EXPIRY,"expiry",Modifier.FINAL).build());
  context.cache.addMethod(MethodSpec.methodBuilder("expiry").addModifiers(context.protectedFinalModifiers()).addStatement("return expiry").returns(EXPIRY).build());
  context.constructor.addStatement("this.timerWheel = builder.expiresVariable() ? new $T(this) : null",TIMER_WHEEL);
  context.cache.addField(FieldSpec.builder(TIMER_WHEEL,"timerWheel",Modifier.FINAL).build());
  context.cache.addMethod(MethodSpec.methodBuilder("timerWheel").addModifiers(context.protectedFinalModifiers()).addStatement("return timerWheel").returns(TIMER_WHEEL).build());
}
