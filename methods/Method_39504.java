@Override public void visitEnum(final String name,final String descriptor,final String value){
  ++numElementValuePairs;
  if (useNamedValues) {
    annotation.putShort(symbolTable.addConstantUtf8(name));
  }
  annotation.put12('e',symbolTable.addConstantUtf8(descriptor)).putShort(symbolTable.addConstantUtf8(value));
}
