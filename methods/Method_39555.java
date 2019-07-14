@Override public void visitNestHost(final String nestHost){
  nestHostClassIndex=symbolTable.addConstantClass(nestHost).index;
}
