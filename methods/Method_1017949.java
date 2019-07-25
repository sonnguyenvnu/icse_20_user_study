/** 
 * Autowire an object
 * @param key   the name of the Spring Bean
 * @param obj   the Bean or Object you want to be included into Spring and autowired
 * @param force Force it to be autowired even if the bean is not registered with the appcontext.  If the key is already registered in spring and this is false it will not autowire.
 * @return the autowired Spring object
 */
public Object autowire(String key,Object obj,boolean force){
  Object bean=null;
  try {
    bean=SpringApplicationContext.getInstance().getBean(key);
  }
 catch (  Exception e) {
  }
  if (bean == null || force) {
    try {
      if (applicationContext == null) {
        initializeSpring();
      }
      if (applicationContext != null) {
        AutowireCapableBeanFactory autowire=getApplicationContext().getAutowireCapableBeanFactory();
        autowire.autowireBean(obj);
        autowire.initializeBean(obj,key);
        return obj;
      }
 else {
        log.error("Unable to autowire {} with Object: {}.  ApplicationContext is null.",key,obj);
      }
    }
 catch (    Exception e) {
      log.error("Unable to autowire {} with Object: {} ",key,obj);
    }
  }
 else   if (bean != null) {
    return bean;
  }
  return null;
}
