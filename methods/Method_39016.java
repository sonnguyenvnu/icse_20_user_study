/** 
 * Binds action annotation and the action config. This can overwrite the default annotation configuration of an annotation.
 */
public void bindAnnotationConfig(final Class<? extends Annotation> annotationType,final Class<? extends ActionConfig> actionConfigClass){
  final ActionConfig actionConfig=registerNewActionConfiguration(actionConfigClass);
  actionConfigs.put(annotationType,actionConfig);
  for (  final AnnotationParser annotationParser : annotationParsers) {
    if (annotationType.equals(annotationParser.getAnnotationType())) {
      return;
    }
  }
  annotationParsers=ArraysUtil.append(annotationParsers,new AnnotationParser(annotationType,Action.class));
}
