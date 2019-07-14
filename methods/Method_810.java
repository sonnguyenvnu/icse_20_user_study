public static int getParserFeatures(Class<?> clazz){
  JSONType annotation=TypeUtils.getAnnotation(clazz,JSONType.class);
  if (annotation == null) {
    return 0;
  }
  return Feature.of(annotation.parseFeatures());
}
