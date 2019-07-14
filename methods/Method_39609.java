@Override public AnnotationVisitor visitTryCatchAnnotation(final int typeRef,final TypePath typePath,final String descriptor,final boolean visible){
  if (visible) {
    return lastCodeRuntimeVisibleTypeAnnotation=AnnotationWriter.create(symbolTable,typeRef,typePath,descriptor,lastCodeRuntimeVisibleTypeAnnotation);
  }
 else {
    return lastCodeRuntimeInvisibleTypeAnnotation=AnnotationWriter.create(symbolTable,typeRef,typePath,descriptor,lastCodeRuntimeInvisibleTypeAnnotation);
  }
}
