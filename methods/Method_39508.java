/** 
 * Returns the size of a Runtime[In]Visible[Type]Annotations attribute containing this annotation and all its <i>predecessors</i> (see  {@link #previousAnnotation}. Also adds the attribute name to the constant pool of the class (if not null).
 * @param attributeName one of "Runtime[In]Visible[Type]Annotations", or null.
 * @return the size in bytes of a Runtime[In]Visible[Type]Annotations attribute containing thisannotation and all its predecessors. This includes the size of the attribute_name_index and attribute_length fields.
 */
int computeAnnotationsSize(final String attributeName){
  if (attributeName != null) {
    symbolTable.addConstantUtf8(attributeName);
  }
  int attributeSize=8;
  AnnotationWriter annotationWriter=this;
  while (annotationWriter != null) {
    attributeSize+=annotationWriter.annotation.length;
    annotationWriter=annotationWriter.previousAnnotation;
  }
  return attributeSize;
}
