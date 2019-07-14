@Override public void visitOpen(final String packaze,final int access,final String... modules){
  opens.putShort(symbolTable.addConstantPackage(packaze).index).putShort(access);
  if (modules == null) {
    opens.putShort(0);
  }
 else {
    opens.putShort(modules.length);
    for (    String module : modules) {
      opens.putShort(symbolTable.addConstantModule(module).index);
    }
  }
  opensCount++;
}
