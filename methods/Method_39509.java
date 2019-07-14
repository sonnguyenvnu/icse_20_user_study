/** 
 * Returns the size of the Runtime[In]Visible[Type]Annotations attributes containing the given annotations and all their <i>predecessors</i> (see  {@link #previousAnnotation}. Also adds the attribute names to the constant pool of the class (if not null).
 * @param lastRuntimeVisibleAnnotation The last runtime visible annotation of a field, method orclass. The previous ones can be accessed with the  {@link #previousAnnotation} field. May be{@literal null}.
 * @param lastRuntimeInvisibleAnnotation The last runtime invisible annotation of this a field,method or class. The previous ones can be accessed with the  {@link #previousAnnotation}field. May be  {@literal null}.
 * @param lastRuntimeVisibleTypeAnnotation The last runtime visible type annotation of this afield, method or class. The previous ones can be accessed with the  {@link #previousAnnotation} field. May be {@literal null}.
 * @param lastRuntimeInvisibleTypeAnnotation The last runtime invisible type annotation of afield, method or class field. The previous ones can be accessed with the  {@link #previousAnnotation} field. May be {@literal null}.
 * @return the size in bytes of a Runtime[In]Visible[Type]Annotations attribute containing thegiven annotations and all their predecessors. This includes the size of the attribute_name_index and attribute_length fields.
 */
static int computeAnnotationsSize(final AnnotationWriter lastRuntimeVisibleAnnotation,final AnnotationWriter lastRuntimeInvisibleAnnotation,final AnnotationWriter lastRuntimeVisibleTypeAnnotation,final AnnotationWriter lastRuntimeInvisibleTypeAnnotation){
  int size=0;
  if (lastRuntimeVisibleAnnotation != null) {
    size+=lastRuntimeVisibleAnnotation.computeAnnotationsSize(Constants.RUNTIME_VISIBLE_ANNOTATIONS);
  }
  if (lastRuntimeInvisibleAnnotation != null) {
    size+=lastRuntimeInvisibleAnnotation.computeAnnotationsSize(Constants.RUNTIME_INVISIBLE_ANNOTATIONS);
  }
  if (lastRuntimeVisibleTypeAnnotation != null) {
    size+=lastRuntimeVisibleTypeAnnotation.computeAnnotationsSize(Constants.RUNTIME_VISIBLE_TYPE_ANNOTATIONS);
  }
  if (lastRuntimeInvisibleTypeAnnotation != null) {
    size+=lastRuntimeInvisibleTypeAnnotation.computeAnnotationsSize(Constants.RUNTIME_INVISIBLE_TYPE_ANNOTATIONS);
  }
  return size;
}
