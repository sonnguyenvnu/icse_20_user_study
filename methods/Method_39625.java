@Override public void visitPackage(final String packaze){
  packageIndex.putShort(symbolTable.addConstantPackage(packaze).index);
  packageCount++;
}
