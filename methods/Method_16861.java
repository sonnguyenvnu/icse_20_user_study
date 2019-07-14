@Override protected void execute(){
  context.constructor.addStatement("this.ticker = builder.getTicker()");
  context.cache.addField(FieldSpec.builder(TICKER,"ticker",Modifier.FINAL).build());
  context.cache.addMethod(MethodSpec.methodBuilder("expirationTicker").addModifiers(context.publicFinalModifiers()).addStatement("return ticker").returns(TICKER).build());
}
