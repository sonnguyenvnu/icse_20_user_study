private static void parse(ITemplateable bean,Cell[] rowArr,Map<String,Integer> propertyMap){
  Field[] fieldArr=bean.getClass().getDeclaredFields();
  int rows=rowArr.length;
  try {
    for (    Field field : fieldArr) {
      Integer index=propertyMap.get(field.getName());
      if (index == null)       continue;
      if (index >= rows)       continue;
      String str=rowArr[index].getContents();
      if (str == null || str.trim().equals(""))       str="";
switch (field.getType().getSimpleName().toLowerCase()) {
case "int":
        bean.getClass().getDeclaredMethod(getSetter(field.getName()),int.class).invoke(bean,str.equals("") ? 0 : Integer.valueOf(str));
      break;
case "long":
    bean.getClass().getDeclaredMethod(getSetter(field.getName()),long.class).invoke(bean,str.equals("") ? 0 : Long.valueOf(str));
  break;
case "double":
bean.getClass().getDeclaredMethod(getSetter(field.getName()),double.class).invoke(bean,str.equals("") ? 0 : Double.valueOf(str));
break;
case "float":
bean.getClass().getDeclaredMethod(getSetter(field.getName()),double.class).invoke(bean,str.equals("") ? 0 : Float.valueOf(str));
break;
case "bigdecimal":
bean.getClass().getDeclaredMethod(getSetter(field.getName()),BigDecimal.class).invoke(bean,str.equals("") ? new BigDecimal(0) : new BigDecimal(str));
break;
case "string":
bean.getClass().getDeclaredMethod(getSetter(field.getName()),String.class).invoke(bean,str);
break;
case "boolean":
bean.getClass().getDeclaredMethod(getSetter(field.getName()),boolean.class).invoke(bean,str.equals("1") ? true : false);
break;
case "date":
bean.getClass().getDeclaredMethod(getSetter(field.getName()),boolean.class).invoke(bean,str.equals("") ? null : new Date(Long.valueOf(str)));
break;
default :
bean.getClass().getDeclaredMethod(getSetter(field.getName()),String.class).invoke(bean,str);
}
}
}
 catch (Exception e) {
e.printStackTrace();
}
}
