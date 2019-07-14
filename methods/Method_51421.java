public void addAttributesTo(Map<PropertyDescriptorField,String> attributes){
  attributes.put(MIN,lowerLimit.toString());
  attributes.put(MAX,upperLimit.toString());
}
