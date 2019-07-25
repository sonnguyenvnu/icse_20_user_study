/** 
 * Autowire an object Force it to be autowired even if the bean is not registered with the appcontext
 */
public static Object autowire(String key,Object obj,boolean force){
  Object bean=null;
  if (key != null) {
    try {
      bean=getBean(key);
    }
 catch (    Exception e) {
    }
  }
  if (bean == null || force) {
    try {
      if (CONTEXT != null) {
        AutowireCapableBeanFactory autowire=getApplicationContext().getAutowireCapableBeanFactory();
        autowire.autowireBean(obj);
        autowire.initializeBean(obj,key);
        return obj;
      }
 else {
        log.error("Unable to autowire {} with Object.  ApplicationContext is null.",key,obj);
      }
    }
 catch (    Exception e) {
      log.error("Unable to autowire {} with Object ",key,obj);
    }
  }
 else   if (bean != null) {
    return bean;
  }
  return null;
}
