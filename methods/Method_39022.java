/** 
 * Resolves action config.
 */
protected ActionConfig resolveActionConfig(final ActionAnnotationValues annotationValues){
  final Class<? extends Annotation> annotationType;
  if (annotationValues == null) {
    annotationType=Action.class;
  }
 else {
    annotationType=annotationValues.annotationType();
  }
  return actionConfigManager.lookup(annotationType);
}
