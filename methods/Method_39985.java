/** 
 * Process all annotations of provided properties.
 */
protected void collectPropertyAnnotationChecks(final List<Check> annChecks,final PropertyDescriptor propertyDescriptor){
  FieldDescriptor fd=propertyDescriptor.getFieldDescriptor();
  if (fd != null) {
    Annotation[] annotations=fd.getField().getAnnotations();
    collectAnnotationChecks(annChecks,propertyDescriptor.getType(),propertyDescriptor.getName(),annotations);
  }
  MethodDescriptor md=propertyDescriptor.getReadMethodDescriptor();
  if (md != null) {
    Annotation[] annotations=md.getMethod().getAnnotations();
    collectAnnotationChecks(annChecks,propertyDescriptor.getType(),propertyDescriptor.getName(),annotations);
  }
  md=propertyDescriptor.getWriteMethodDescriptor();
  if (md != null) {
    Annotation[] annotations=md.getMethod().getAnnotations();
    collectAnnotationChecks(annChecks,propertyDescriptor.getType(),propertyDescriptor.getName(),annotations);
  }
}
