/** 
 * Puts a Runtime[In]Visible[Type]Annotations attribute containing this annotations and all its <i>predecessors</i> (see  {@link #previousAnnotation} in the given ByteVector. Annotations areput in the same order they have been visited.
 * @param attributeNameIndex the constant pool index of the attribute name (one of"Runtime[In]Visible[Type]Annotations").
 * @param output where the attribute must be put.
 */
void putAnnotations(final int attributeNameIndex,final ByteVector output){
  int attributeLength=2;
  int numAnnotations=0;
  AnnotationWriter annotationWriter=this;
  AnnotationWriter firstAnnotation=null;
  while (annotationWriter != null) {
    annotationWriter.visitEnd();
    attributeLength+=annotationWriter.annotation.length;
    numAnnotations++;
    firstAnnotation=annotationWriter;
    annotationWriter=annotationWriter.previousAnnotation;
  }
  output.putShort(attributeNameIndex);
  output.putInt(attributeLength);
  output.putShort(numAnnotations);
  annotationWriter=firstAnnotation;
  while (annotationWriter != null) {
    output.putByteArray(annotationWriter.annotation.data,0,annotationWriter.annotation.length);
    annotationWriter=annotationWriter.nextAnnotation;
  }
}
