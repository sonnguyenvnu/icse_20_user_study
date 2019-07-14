/** 
 * ????????????????????????????getter/setter???????
 * @param src          ?????
 * @param dst          ?????
 * @param ignoreFields ?????
 * @param < T >          ??
 * @return             ????????
 */
public static <T>List<String> getModifiedFields(T src,T dst,String... ignoreFields){
  Class clazz=src.getClass();
  Method[] methods=clazz.getMethods();
  List<String> ignoreFiledList=Arrays.asList(ignoreFields);
  List<String> modifiedFields=new ArrayList<String>();
  for (  Method getterMethod : methods) {
    if (Modifier.isStatic(getterMethod.getModifiers()) || !ReflectUtils.isBeanPropertyReadMethod(getterMethod)) {
      continue;
    }
    String propertyName=ReflectUtils.getPropertyNameFromBeanReadMethod(getterMethod);
    if (ignoreFiledList.contains(propertyName)) {
      continue;
    }
    Class returnType=getterMethod.getReturnType();
    try {
      Method setterMethod=ReflectUtils.getPropertySetterMethod(clazz,propertyName,returnType);
      if (setterMethod != null) {
        Object srcVal=getterMethod.invoke(src);
        Object dstVal=getterMethod.invoke(dst);
        if (srcVal == null) {
          if (dstVal != null) {
            modifiedFields.add(propertyName);
          }
        }
 else {
          if (dstVal == null) {
            modifiedFields.add(propertyName);
          }
 else {
            if (!srcVal.equals(dstVal)) {
              modifiedFields.add(propertyName);
            }
          }
        }
      }
    }
 catch (    Exception ignore) {
    }
  }
  return modifiedFields;
}
