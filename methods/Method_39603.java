@Override public AnnotationVisitor visitParameterAnnotation(final int parameter,final String annotationDescriptor,final boolean visible){
  if (visible) {
    if (lastRuntimeVisibleParameterAnnotations == null) {
      lastRuntimeVisibleParameterAnnotations=new AnnotationWriter[Type.getArgumentTypes(descriptor).length];
    }
    return lastRuntimeVisibleParameterAnnotations[parameter]=AnnotationWriter.create(symbolTable,annotationDescriptor,lastRuntimeVisibleParameterAnnotations[parameter]);
  }
 else {
    if (lastRuntimeInvisibleParameterAnnotations == null) {
      lastRuntimeInvisibleParameterAnnotations=new AnnotationWriter[Type.getArgumentTypes(descriptor).length];
    }
    return lastRuntimeInvisibleParameterAnnotations[parameter]=AnnotationWriter.create(symbolTable,annotationDescriptor,lastRuntimeInvisibleParameterAnnotations[parameter]);
  }
}
