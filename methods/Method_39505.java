@Override public AnnotationVisitor visitAnnotation(final String name,final String descriptor){
  ++numElementValuePairs;
  if (useNamedValues) {
    annotation.putShort(symbolTable.addConstantUtf8(name));
  }
  annotation.put12('@',symbolTable.addConstantUtf8(descriptor)).putShort(0);
  return new AnnotationWriter(symbolTable,true,annotation,null);
}
