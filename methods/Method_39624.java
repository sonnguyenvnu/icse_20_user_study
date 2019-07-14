@Override public void visitMainClass(final String mainClass){
  this.mainClassIndex=symbolTable.addConstantClass(mainClass).index;
}
