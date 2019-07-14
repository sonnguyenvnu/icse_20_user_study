@Override public AnnotationVisitor visitLocalVariableAnnotation(final int typeRef,final TypePath typePath,final Label[] start,final Label[] end,final int[] index,final String descriptor,final boolean visible){
  ByteVector typeAnnotation=new ByteVector();
  typeAnnotation.putByte(typeRef >>> 24).putShort(start.length);
  for (int i=0; i < start.length; ++i) {
    typeAnnotation.putShort(start[i].bytecodeOffset).putShort(end[i].bytecodeOffset - start[i].bytecodeOffset).putShort(index[i]);
  }
  TypePath.put(typePath,typeAnnotation);
  typeAnnotation.putShort(symbolTable.addConstantUtf8(descriptor)).putShort(0);
  if (visible) {
    return lastCodeRuntimeVisibleTypeAnnotation=new AnnotationWriter(symbolTable,true,typeAnnotation,lastCodeRuntimeVisibleTypeAnnotation);
  }
 else {
    return lastCodeRuntimeInvisibleTypeAnnotation=new AnnotationWriter(symbolTable,true,typeAnnotation,lastCodeRuntimeInvisibleTypeAnnotation);
  }
}
