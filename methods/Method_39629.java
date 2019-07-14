@Override public void visitUse(final String service){
  usesIndex.putShort(symbolTable.addConstantClass(service).index);
  usesCount++;
}
