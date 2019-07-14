/** 
 * @deprecated
 */
public static int getSerializeFeatures(Class<?> clazz){
  JSONType annotation=TypeUtils.getAnnotation(clazz,JSONType.class);
  if (annotation == null) {
    return 0;
  }
  return SerializerFeature.of(annotation.serialzeFeatures());
}
