protected Object processValue(JSONSerializer jsonBeanDeser,BeanContext beanContext,Object object,String key,Object propertyValue){
  if (propertyValue != null) {
    if ((jsonBeanDeser.out.writeNonStringValueAsString || (beanContext != null && (beanContext.getFeatures() & SerializerFeature.WriteNonStringValueAsString.mask) != 0)) && (propertyValue instanceof Number || propertyValue instanceof Boolean)) {
      String format=null;
      if (propertyValue instanceof Number && beanContext != null) {
        format=beanContext.getFormat();
      }
      if (format != null) {
        propertyValue=new DecimalFormat(format).format(propertyValue);
      }
 else {
        propertyValue=propertyValue.toString();
      }
    }
 else     if (beanContext != null && beanContext.isJsonDirect()) {
      String jsonStr=(String)propertyValue;
      propertyValue=JSON.parse(jsonStr);
    }
  }
  if (jsonBeanDeser.valueFilters != null) {
    for (    ValueFilter valueFilter : jsonBeanDeser.valueFilters) {
      propertyValue=valueFilter.process(object,key,propertyValue);
    }
  }
  List<ValueFilter> valueFilters=this.valueFilters;
  if (valueFilters != null) {
    for (    ValueFilter valueFilter : valueFilters) {
      propertyValue=valueFilter.process(object,key,propertyValue);
    }
  }
  if (jsonBeanDeser.contextValueFilters != null) {
    for (    ContextValueFilter valueFilter : jsonBeanDeser.contextValueFilters) {
      propertyValue=valueFilter.process(beanContext,object,key,propertyValue);
    }
  }
  if (this.contextValueFilters != null) {
    for (    ContextValueFilter valueFilter : this.contextValueFilters) {
      propertyValue=valueFilter.process(beanContext,object,key,propertyValue);
    }
  }
  return propertyValue;
}
