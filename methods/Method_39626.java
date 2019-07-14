@Override public void visitRequire(final String module,final int access,final String version){
  requires.putShort(symbolTable.addConstantModule(module).index).putShort(access).putShort(version == null ? 0 : symbolTable.addConstantUtf8(version));
  requiresCount++;
}
