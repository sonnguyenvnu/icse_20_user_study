/** 
 * class level serializer feature config
 * @since 1.2.12
 */
public void config(Class<?> clazz,SerializerFeature feature,boolean value){
  ObjectSerializer serializer=getObjectWriter(clazz,false);
  if (serializer == null) {
    SerializeBeanInfo beanInfo=TypeUtils.buildBeanInfo(clazz,null,propertyNamingStrategy);
    if (value) {
      beanInfo.features|=feature.mask;
    }
 else {
      beanInfo.features&=~feature.mask;
    }
    serializer=this.createJavaBeanSerializer(beanInfo);
    put(clazz,serializer);
    return;
  }
  if (serializer instanceof JavaBeanSerializer) {
    JavaBeanSerializer javaBeanSerializer=(JavaBeanSerializer)serializer;
    SerializeBeanInfo beanInfo=javaBeanSerializer.beanInfo;
    int originalFeaturs=beanInfo.features;
    if (value) {
      beanInfo.features|=feature.mask;
    }
 else {
      beanInfo.features&=~feature.mask;
    }
    if (originalFeaturs == beanInfo.features) {
      return;
    }
    Class<?> serializerClass=serializer.getClass();
    if (serializerClass != JavaBeanSerializer.class) {
      ObjectSerializer newSerializer=this.createJavaBeanSerializer(beanInfo);
      this.put(clazz,newSerializer);
    }
  }
}
