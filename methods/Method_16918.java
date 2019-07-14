private void addVariableTime(String varName){
  MethodSpec getter=MethodSpec.methodBuilder("getVariableTime").addModifiers(Modifier.PUBLIC).addStatement("return $T.UNSAFE.getLong(this, $N)",UNSAFE_ACCESS,offsetName(varName)).returns(long.class).build();
  MethodSpec setter=MethodSpec.methodBuilder("setVariableTime").addModifiers(Modifier.PUBLIC).addParameter(long.class,varName).addStatement("$T.UNSAFE.putLong(this, $N, $N)",UNSAFE_ACCESS,offsetName(varName),varName).build();
  MethodSpec cas=MethodSpec.methodBuilder("casVariableTime").addModifiers(Modifier.PUBLIC).addParameter(long.class,"expect").addParameter(long.class,"update").returns(boolean.class).addStatement("return ($N == $N)\n&& $T.UNSAFE.compareAndSwapLong(this, $N, $N, $N)",varName,"expect",UNSAFE_ACCESS,offsetName(varName),"expect","update").build();
  context.nodeSubtype.addMethod(getter).addMethod(setter).addMethod(cas);
}
