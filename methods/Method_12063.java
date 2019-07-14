/** 
 * Creates the AnnotationValidator specified by the value in {@link org.junit.validator.ValidateWith}. Instances are cached.
 * @return An instance of the AnnotationValidator.
 * @since 4.12
 */
public AnnotationValidator createAnnotationValidator(ValidateWith validateWithAnnotation){
  AnnotationValidator validator=VALIDATORS_FOR_ANNOTATION_TYPES.get(validateWithAnnotation);
  if (validator != null) {
    return validator;
  }
  Class<? extends AnnotationValidator> clazz=validateWithAnnotation.value();
  try {
    AnnotationValidator annotationValidator=clazz.newInstance();
    VALIDATORS_FOR_ANNOTATION_TYPES.putIfAbsent(validateWithAnnotation,annotationValidator);
    return VALIDATORS_FOR_ANNOTATION_TYPES.get(validateWithAnnotation);
  }
 catch (  Exception e) {
    throw new RuntimeException("Exception received when creating AnnotationValidator class " + clazz.getName(),e);
  }
}
