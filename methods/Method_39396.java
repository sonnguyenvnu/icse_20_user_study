/** 
 * Reads annotation value and returns  {@code null} if value is empty.
 */
private String readAnnotationValue(final PetiteInject annotation){
  String value=annotation.value().trim();
  if (value.isEmpty()) {
    return null;
  }
  return value;
}
