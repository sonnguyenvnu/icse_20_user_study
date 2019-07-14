/** 
 * Puts a Runtime[In]VisibleParameterAnnotations attribute containing all the annotation lists from the given AnnotationWriter sub-array in the given ByteVector.
 * @param attributeNameIndex constant pool index of the attribute name (one ofRuntime[In]VisibleParameterAnnotations).
 * @param annotationWriters an array of AnnotationWriter lists (designated by their <i>last</i>element).
 * @param annotableParameterCount the number of elements in annotationWriters to put (elements[0..annotableParameterCount[ are put).
 * @param output where the attribute must be put.
 */
static void putParameterAnnotations(final int attributeNameIndex,final AnnotationWriter[] annotationWriters,final int annotableParameterCount,final ByteVector output){
  int attributeLength=1 + 2 * annotableParameterCount;
  for (int i=0; i < annotableParameterCount; ++i) {
    AnnotationWriter annotationWriter=annotationWriters[i];
    attributeLength+=annotationWriter == null ? 0 : annotationWriter.computeAnnotationsSize(null) - 8;
  }
  output.putShort(attributeNameIndex);
  output.putInt(attributeLength);
  output.putByte(annotableParameterCount);
  for (int i=0; i < annotableParameterCount; ++i) {
    AnnotationWriter annotationWriter=annotationWriters[i];
    AnnotationWriter firstAnnotation=null;
    int numAnnotations=0;
    while (annotationWriter != null) {
      annotationWriter.visitEnd();
      numAnnotations++;
      firstAnnotation=annotationWriter;
      annotationWriter=annotationWriter.previousAnnotation;
    }
    output.putShort(numAnnotations);
    annotationWriter=firstAnnotation;
    while (annotationWriter != null) {
      output.putByteArray(annotationWriter.annotation.data,0,annotationWriter.annotation.length);
      annotationWriter=annotationWriter.nextAnnotation;
    }
  }
}
