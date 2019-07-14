/** 
 * Populates the builder with extracted fields. To be overridden. 
 */
protected void populate(T builder,Map<PropertyDescriptorField,String> fields){
  builder.desc(fields.get(PropertyDescriptorField.DESCRIPTION));
  if (fields.containsKey(PropertyDescriptorField.UI_ORDER)) {
    builder.uiOrder(Float.parseFloat(fields.get(PropertyDescriptorField.UI_ORDER)));
  }
}
