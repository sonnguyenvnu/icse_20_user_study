public static JavaBeanInfo build(Class<?> clazz,Type type,PropertyNamingStrategy propertyNamingStrategy,boolean fieldBased,boolean compatibleWithJavaBean){
  return build(clazz,type,propertyNamingStrategy,fieldBased,compatibleWithJavaBean,false);
}
