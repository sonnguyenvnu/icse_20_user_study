private void addFrequencySketch(){
  context.cache.addField(FieldSpec.builder(FREQUENCY_SKETCH,"sketch",Modifier.FINAL).build());
  context.constructor.addCode(CodeBlock.builder().addStatement("this.sketch = new $T()",FREQUENCY_SKETCH).beginControlFlow("if (builder.hasInitialCapacity())").addStatement("long capacity = Math.min($L, $L)","builder.getMaximum()","builder.getInitialCapacity()").addStatement("this.sketch.ensureCapacity(capacity)").endControlFlow().build());
  context.cache.addMethod(MethodSpec.methodBuilder("frequencySketch").addModifiers(context.protectedFinalModifiers()).addStatement("return sketch").returns(FREQUENCY_SKETCH).build());
}
