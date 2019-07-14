/** 
 * Reads method's alias value.
 */
protected String parseMethodAlias(final ActionAnnotationValues annotationValues){
  String alias=null;
  if (annotationValues != null) {
    alias=annotationValues.alias();
  }
  return alias;
}
