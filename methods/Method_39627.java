@Override public void visitExport(final String packaze,final int access,final String... modules){
  exports.putShort(symbolTable.addConstantPackage(packaze).index).putShort(access);
  if (modules == null) {
    exports.putShort(0);
  }
 else {
    exports.putShort(modules.length);
    for (    String module : modules) {
      exports.putShort(symbolTable.addConstantModule(module).index);
    }
  }
  exportsCount++;
}
