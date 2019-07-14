/** 
 * Detects &#64;FrameworkEndpoint annotations in handler beans.
 * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping#isHandler(java.lang.Class)
 */
@Override protected boolean isHandler(Class<?> beanType){
  return AnnotationUtils.findAnnotation(beanType,FrameworkEndpoint.class) != null;
}
