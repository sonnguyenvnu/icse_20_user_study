private void addStatsTicker(){
  context.cache.addMethod(MethodSpec.methodBuilder("statsTicker").addModifiers(context.publicFinalModifiers()).addStatement("return $T.systemTicker()",TICKER).returns(TICKER).build());
}
