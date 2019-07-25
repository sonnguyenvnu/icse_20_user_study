/** 
 * Reset all post processors associated with a bean factory.
 * @param beanFactory beanFactory to use
 */
public static void reset(DefaultListableBeanFactory beanFactory){
  Class<?> c=getReflectionUtilsClassOrNull();
  if (c != null) {
    try {
      Method m=c.getDeclaredMethod("clearCache");
      m.invoke(c);
    }
 catch (    Exception version42Failed) {
      try {
        Field declaredMethodsCache=c.getDeclaredField("declaredMethodsCache");
        declaredMethodsCache.setAccessible(true);
        ((Map)declaredMethodsCache.get(null)).clear();
        Field declaredFieldsCache=c.getDeclaredField("declaredFieldsCache");
        declaredFieldsCache.setAccessible(true);
        ((Map)declaredFieldsCache.get(null)).clear();
      }
 catch (      Exception version40Failed) {
        LOGGER.debug("Failed to clear internal method/field cache, it's normal with spring 4.1x or lower",version40Failed);
      }
    }
    LOGGER.trace("Cleared Spring 4.2+ internal method/field cache.");
  }
  for (  BeanPostProcessor bpp : beanFactory.getBeanPostProcessors()) {
    if (bpp instanceof AutowiredAnnotationBeanPostProcessor) {
      resetAutowiredAnnotationBeanPostProcessorCache((AutowiredAnnotationBeanPostProcessor)bpp);
    }
 else     if (bpp instanceof InitDestroyAnnotationBeanPostProcessor) {
      resetInitDestroyAnnotationBeanPostProcessorCache((InitDestroyAnnotationBeanPostProcessor)bpp);
    }
  }
}
