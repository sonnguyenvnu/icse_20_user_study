/** 
 * Visits replacement code for  {@link ProxyTarget#targetMethodAnnotation(String,String)}.
 */
public static void targetMethodAnnotation(final MethodVisitor mv,final MethodInfo methodInfo,final String[] args){
  AnnotationInfo[] anns=methodInfo.getAnnotations();
  if (anns != null) {
    targetAnnotation(mv,anns,args);
  }
}
