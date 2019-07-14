/** 
 * Checks whether any annotation in ignoredAnnotationsDescriptor is present on the node.
 * @param node the node to check
 * @return <code>true</code> if the annotation has been found, otherwise <code>false</code>
 */
protected boolean hasIgnoredAnnotation(Annotatable node){
  return node.isAnyAnnotationPresent(getProperty(ignoredAnnotationsDescriptor));
}
