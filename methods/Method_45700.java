/** 
 * ??????
 * @param bean  ??
 * @param name  ???
 * @param clazz ?????
 * @param < T >   ?????????
 * @return ???
 * @throws Exception ????
 */
public static <T>T getProperty(Object bean,String name,Class<T> clazz) throws Exception {
  Method method=ReflectUtils.getPropertyGetterMethod(bean.getClass(),name);
  if (method.isAccessible()) {
    return (T)method.invoke(bean);
  }
 else {
    try {
      method.setAccessible(true);
      return (T)method.invoke(bean);
    }
  finally {
      method.setAccessible(false);
    }
  }
}
