/** 
 * Puts the Runtime[In]Visible[Type]Annotations attributes containing the given annotations and all their <i>predecessors</i> (see  {@link #previousAnnotation} in the given ByteVector.Annotations are put in the same order they have been visited.
 * @param symbolTable where the constants used in the AnnotationWriter instances are stored.
 * @param lastRuntimeVisibleAnnotation The last runtime visible annotation of a field, method orclass. The previous ones can be accessed with the  {@link #previousAnnotation} field. May be{@literal null}.
 * @param lastRuntimeInvisibleAnnotation The last runtime invisible annotation of this a field,method or class. The previous ones can be accessed with the  {@link #previousAnnotation}field. May be  {@literal null}.
 * @param lastRuntimeVisibleTypeAnnotation The last runtime visible type annotation of this afield, method or class. The previous ones can be accessed with the  {@link #previousAnnotation} field. May be {@literal null}.
 * @param lastRuntimeInvisibleTypeAnnotation The last runtime invisible type annotation of afield, method or class field. The previous ones can be accessed with the  {@link #previousAnnotation} field. May be {@literal null}.
 * @param output where the attributes must be put.
 */
static void putAnnotations(final SymbolTable symbolTable,final AnnotationWriter lastRuntimeVisibleAnnotation,final AnnotationWriter lastRuntimeInvisibleAnnotation,final AnnotationWriter lastRuntimeVisibleTypeAnnotation,final AnnotationWriter lastRuntimeInvisibleTypeAnnotation,final ByteVector output){
  if (lastRuntimeVisibleAnnotation != null) {
    lastRuntimeVisibleAnnotation.putAnnotations(symbolTable.addConstantUtf8(Constants.RUNTIME_VISIBLE_ANNOTATIONS),output);
  }
  if (lastRuntimeInvisibleAnnotation != null) {
    lastRuntimeInvisibleAnnotation.putAnnotations(symbolTable.addConstantUtf8(Constants.RUNTIME_INVISIBLE_ANNOTATIONS),output);
  }
  if (lastRuntimeVisibleTypeAnnotation != null) {
    lastRuntimeVisibleTypeAnnotation.putAnnotations(symbolTable.addConstantUtf8(Constants.RUNTIME_VISIBLE_TYPE_ANNOTATIONS),output);
  }
  if (lastRuntimeInvisibleTypeAnnotation != null) {
    lastRuntimeInvisibleTypeAnnotation.putAnnotations(symbolTable.addConstantUtf8(Constants.RUNTIME_INVISIBLE_TYPE_ANNOTATIONS),output);
  }
}
