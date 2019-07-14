void generate() throws IOException {
  nodeFactory=TypeSpec.interfaceBuilder("NodeFactory").addTypeVariable(kTypeVar).addTypeVariable(vTypeVar);
  addClassJavaDoc();
  addConstants();
  addKeyMethods();
  generatedNodes();
  addNewFactoryMethods();
  writeJavaFile();
}
