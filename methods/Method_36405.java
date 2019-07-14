/** 
 * Get spring object
 * @param type type
 * @param beanName bean name
 * @param applicationContext application context
 * @return spring object
 */
public static Object getSpringObject(Class type,String beanName,ApplicationContext applicationContext){
  if (type == Resource.class) {
    return applicationContext.getResource(beanName);
  }
 else {
    return applicationContext.getBean(beanName,type);
  }
}
