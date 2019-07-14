private MethodSpec newNodeMethod(ParameterSpec... keyParams){
  return MethodSpec.methodBuilder("newNode").addJavadoc("Returns a node optimized for the specified features.\n").addModifiers(Modifier.PUBLIC,Modifier.ABSTRACT).addParameters(ImmutableList.copyOf(keyParams)).addParameter(valueSpec).addParameter(valueRefQueueSpec).addParameter(int.class,"weight").addParameter(long.class,"now").returns(NODE).build();
}
