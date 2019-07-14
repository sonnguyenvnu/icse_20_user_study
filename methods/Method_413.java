@SuppressWarnings({"rawtypes"}) protected boolean removePropertyValue(Object parent,String name,boolean deep){
  if (parent instanceof Map) {
    Object origin=((Map)parent).remove(name);
    boolean found=origin != null;
    if (deep) {
      for (      Object item : ((Map)parent).values()) {
        removePropertyValue(item,name,deep);
      }
    }
    return found;
  }
  ObjectDeserializer derializer=parserConfig.getDeserializer(parent.getClass());
  JavaBeanDeserializer beanDerializer=null;
  if (derializer instanceof JavaBeanDeserializer) {
    beanDerializer=(JavaBeanDeserializer)derializer;
  }
  if (beanDerializer != null) {
    FieldDeserializer fieldDeserializer=beanDerializer.getFieldDeserializer(name);
    boolean found=false;
    if (fieldDeserializer != null) {
      fieldDeserializer.setValue(parent,null);
      found=true;
    }
    if (deep) {
      Collection<Object> propertyValues=this.getPropertyValues(parent);
      for (      Object item : propertyValues) {
        if (item == null) {
          continue;
        }
        removePropertyValue(item,name,deep);
      }
    }
    return found;
  }
  if (deep) {
    return false;
  }
  throw new UnsupportedOperationException();
}
