/** 
 * Returns  {@code true} if type is a Kotlin class.
 */
public static boolean isKotlinClass(final Class type){
  final Annotation[] annotations=type.getAnnotations();
  for (  Annotation annotation : annotations) {
    if (annotation.annotationType().getName().equals("kotlin.Metadata")) {
      return true;
    }
  }
  return false;
}
