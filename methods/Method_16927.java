private MethodSpec.Builder newNode(ParameterSpec... keyParams){
  return MethodSpec.methodBuilder("newNode").addModifiers(Modifier.PUBLIC).addParameters(ImmutableList.copyOf(keyParams)).addParameter(valueSpec).addParameter(valueRefQueueSpec).addParameter(int.class,"weight").addParameter(long.class,"now").returns(NODE);
}
