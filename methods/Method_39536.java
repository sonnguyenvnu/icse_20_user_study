/** 
 * Reads the element values of a JVMS 'annotation' structure and makes the given visitor visit them. This method can also be used to read the values of the JVMS 'array_value' field of an annotation's 'element_value'.
 * @param annotationVisitor the visitor that must visit the values.
 * @param annotationOffset the start offset of an 'annotation' structure (excluding its type_indexfield) or of an 'array_value' structure.
 * @param named if the annotation values are named or not. This should be true to parse the valuesof a JVMS 'annotation' structure, and false to parse the JVMS 'array_value' of an annotation's element_value.
 * @param charBuffer the buffer used to read strings in the constant pool.
 * @return the end offset of the JVMS 'annotation' or 'array_value' structure.
 */
private int readElementValues(final AnnotationVisitor annotationVisitor,final int annotationOffset,final boolean named,final char[] charBuffer){
  int currentOffset=annotationOffset;
  int numElementValuePairs=readUnsignedShort(currentOffset);
  currentOffset+=2;
  if (named) {
    while (numElementValuePairs-- > 0) {
      String elementName=readUTF8(currentOffset,charBuffer);
      currentOffset=readElementValue(annotationVisitor,currentOffset + 2,elementName,charBuffer);
    }
  }
 else {
    while (numElementValuePairs-- > 0) {
      currentOffset=readElementValue(annotationVisitor,currentOffset,null,charBuffer);
    }
  }
  if (annotationVisitor != null) {
    annotationVisitor.visitEnd();
  }
  return currentOffset;
}
