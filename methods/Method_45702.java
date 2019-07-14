/** 
 * ??????????????????????getter/setter???????
 * @param src          ????
 * @param dst          ????
 * @param ignoreFields ?????
 */
public static void copyProperties(Object src,Object dst,String... ignoreFields){
  Class srcClazz=src.getClass();
  Class distClazz=dst.getClass();
  Method[] methods=distClazz.getMethods();
  List<String> ignoreFiledList=Arrays.asList(ignoreFields);
  for (  Method dstMethod : methods) {
    if (Modifier.isStatic(dstMethod.getModifiers()) || !ReflectUtils.isBeanPropertyReadMethod(dstMethod)) {
      continue;
    }
    String propertyName=ReflectUtils.getPropertyNameFromBeanReadMethod(dstMethod);
    if (ignoreFiledList.contains(propertyName)) {
      continue;
    }
    Class dstReturnType=dstMethod.getReturnType();
    try {
      Method dstSetterMethod=ReflectUtils.getPropertySetterMethod(distClazz,propertyName,dstReturnType);
      if (dstSetterMethod != null) {
        Method srcGetterMethod=ReflectUtils.getPropertyGetterMethod(srcClazz,propertyName);
        Class srcReturnType=srcGetterMethod.getReturnType();
        if (srcReturnType.equals(dstReturnType)) {
          Object val=srcGetterMethod.invoke(src);
          if (val != null) {
            dstSetterMethod.invoke(dst,val);
          }
        }
      }
    }
 catch (    Exception ignore) {
    }
  }
}
