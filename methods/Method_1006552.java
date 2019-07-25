/** 
 * Returns the type converters from the annotations present on the specified element.
 * @param element the method or field annotated with {@code @Option} or {@code @Parameters}
 * @return the type converters or an empty array if not found
 */
@SuppressWarnings("unchecked") public static TypeConverterMetaData[] extract(Element element){
  List<? extends AnnotationMirror> annotationMirrors=element.getAnnotationMirrors();
  for (  AnnotationMirror mirror : annotationMirrors) {
    DeclaredType annotationType=mirror.getAnnotationType();
    if (TypeUtil.isOption(annotationType) || TypeUtil.isParameter(annotationType)) {
      Map<? extends ExecutableElement,? extends AnnotationValue> elementValues=mirror.getElementValues();
      for (      ExecutableElement attribute : elementValues.keySet()) {
        if ("converter".equals(attribute.getSimpleName().toString())) {
          AnnotationValue list=elementValues.get(attribute);
          List<AnnotationValue> typeMirrors=(List<AnnotationValue>)list.getValue();
          List<TypeConverterMetaData> result=new ArrayList<TypeConverterMetaData>();
          for (          AnnotationValue annotationValue : typeMirrors) {
            result.add(new TypeConverterMetaData((TypeMirror)annotationValue));
          }
          return result.toArray(new TypeConverterMetaData[0]);
        }
      }
    }
  }
  return new TypeConverterMetaData[0];
}
