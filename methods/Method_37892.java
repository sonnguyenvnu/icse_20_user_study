/** 
 * Reads annotation value. Returns <code>null</code> on error (e.g. when value name not found).
 */
public static Object readAnnotationValue(final Annotation annotation,final String name){
  try {
    Method method=annotation.annotationType().getDeclaredMethod(name);
    return method.invoke(annotation);
  }
 catch (  Exception ignore) {
    return null;
  }
}
