private void addNewFactoryMethods(){
  nodeFactory.addMethod(MethodSpec.methodBuilder("newFactory").addJavadoc("Returns a factory optimized for the specified features.\n").addModifiers(Modifier.PUBLIC,Modifier.STATIC).addTypeVariable(kTypeVar).addTypeVariable(vTypeVar).addParameter(BUILDER_PARAM).addParameter(boolean.class,"isAsync").addCode(NodeSelectorCode.get()).returns(NODE_FACTORY).build());
  nodeFactory.addMethod(MethodSpec.methodBuilder("weakValues").addJavadoc("Returns whether this factory supports weak values.\n").addModifiers(Modifier.PUBLIC,Modifier.DEFAULT).addStatement("return false").returns(boolean.class).build());
  nodeFactory.addMethod(MethodSpec.methodBuilder("softValues").addJavadoc("Returns whether this factory supports soft values.\n").addModifiers(Modifier.PUBLIC,Modifier.DEFAULT).addStatement("return false").returns(boolean.class).build());
}
