private void addStatsCounter(){
  context.constructor.addStatement("this.statsCounter = builder.getStatsCounterSupplier().get()");
  context.cache.addField(FieldSpec.builder(STATS_COUNTER,"statsCounter",Modifier.FINAL).build());
  context.cache.addMethod(MethodSpec.methodBuilder("statsCounter").addModifiers(context.publicFinalModifiers()).addStatement("return statsCounter").returns(STATS_COUNTER).build());
}
