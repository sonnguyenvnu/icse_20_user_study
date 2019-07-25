private static void replace(Field[] fields,Object javaBean,Set<Integer> referenceCounter) throws IllegalArgumentException, IllegalAccessException {
  if (null != fields && fields.length > 0) {
    for (    Field field : fields) {
      field.setAccessible(true);
      if (null != field && null != javaBean) {
        Object value=field.get(javaBean);
        if (null != value) {
          Class<?> type=value.getClass();
          if (type.isArray()) {
            int len=Array.getLength(value);
            for (int i=0; i < len; i++) {
              Object arrayObject=Array.get(value,i);
              SensitiveInfoUtils.replace(SensitiveInfoUtils.findAllField(arrayObject.getClass()),arrayObject,referenceCounter);
            }
          }
 else           if (value instanceof Collection<?>) {
            Collection<?> c=(Collection<?>)value;
            Iterator<?> it=c.iterator();
            while (it.hasNext()) {
              Object collectionObj=it.next();
              SensitiveInfoUtils.replace(SensitiveInfoUtils.findAllField(collectionObj.getClass()),collectionObj,referenceCounter);
            }
          }
 else           if (value instanceof Map<?,?>) {
            Map<?,?> m=(Map<?,?>)value;
            Set<?> set=m.entrySet();
            for (            Object o : set) {
              Entry<?,?> entry=(Entry<?,?>)o;
              Object mapVal=entry.getValue();
              SensitiveInfoUtils.replace(SensitiveInfoUtils.findAllField(mapVal.getClass()),mapVal,referenceCounter);
            }
          }
 else           if (!type.isPrimitive() && !StringUtils.startsWith(type.getPackage().getName(),"javax.") && !StringUtils.startsWith(type.getPackage().getName(),"java.") && !StringUtils.startsWith(field.getType().getName(),"javax.") && !StringUtils.startsWith(field.getName(),"java.") && referenceCounter.add(value.hashCode())) {
            SensitiveInfoUtils.replace(SensitiveInfoUtils.findAllField(type),value,referenceCounter);
          }
        }
        SensitiveInfo annotation=field.getAnnotation(SensitiveInfo.class);
        if (field.getType().equals(String.class) && null != annotation) {
          String valueStr=(String)value;
          if (StringUtils.isNotBlank(valueStr)) {
switch (annotation.type()) {
case CHINESE_NAME:
{
                field.set(javaBean,SensitiveInfoUtils.chineseName(valueStr));
                break;
              }
case ID_CARD:
{
              field.set(javaBean,SensitiveInfoUtils.idCardNum(valueStr));
              break;
            }
case FIXED_PHONE:
{
            field.set(javaBean,SensitiveInfoUtils.fixedPhone(valueStr));
            break;
          }
case MOBILE_PHONE:
{
          field.set(javaBean,SensitiveInfoUtils.mobilePhone(valueStr));
          break;
        }
case ADDRESS:
{
        field.set(javaBean,SensitiveInfoUtils.address(valueStr,4));
        break;
      }
case EMAIL:
{
      field.set(javaBean,SensitiveInfoUtils.email(valueStr));
      break;
    }
case BANK_CARD:
{
    field.set(javaBean,SensitiveInfoUtils.bankCard(valueStr));
    break;
  }
case CNAPS_CODE:
{
  field.set(javaBean,SensitiveInfoUtils.cnapsCode(valueStr));
  break;
}
}
}
}
}
}
}
}
