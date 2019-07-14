/** 
 * Registers action configuration for given annotation. New  {@link ActionConfig} is createdand stored.
 */
public void registerAnnotation(final Class<? extends Annotation> annotationType){
  final ActionConfiguredBy actionConfiguredBy=annotationType.getAnnotation(ActionConfiguredBy.class);
  if (actionConfiguredBy == null) {
    throw new MadvocException("Action annotation is missing it's " + ActionConfiguredBy.class.getSimpleName() + " configuration.");
  }
  bindAnnotationConfig(annotationType,actionConfiguredBy.value());
}
