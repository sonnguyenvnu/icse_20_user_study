/** 
 * Constructs an annotation object.
 * @param cl        class loader for obtaining annotation types.
 * @param clazz     the annotation type.
 * @param cp        class pool for containing an annotationtype (or null).
 * @param anon      the annotation.
 * @return the annotation
 */
public static Object make(ClassLoader cl,Class<?> clazz,ClassPool cp,Annotation anon) throws IllegalArgumentException {
  AnnotationImpl handler=new AnnotationImpl(anon,cp,cl);
  return Proxy.newProxyInstance(cl,new Class[]{clazz},handler);
}
