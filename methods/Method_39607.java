@Override public AnnotationVisitor visitInsnAnnotation(final int typeRef,final TypePath typePath,final String descriptor,final boolean visible){
  if (visible) {
    return lastCodeRuntimeVisibleTypeAnnotation=AnnotationWriter.create(symbolTable,(typeRef & 0xFF0000FF) | (lastBytecodeOffset << 8),typePath,descriptor,lastCodeRuntimeVisibleTypeAnnotation);
  }
 else {
    return lastCodeRuntimeInvisibleTypeAnnotation=AnnotationWriter.create(symbolTable,(typeRef & 0xFF0000FF) | (lastBytecodeOffset << 8),typePath,descriptor,lastCodeRuntimeInvisibleTypeAnnotation);
  }
}
