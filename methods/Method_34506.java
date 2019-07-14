private static <S>S initializeProperties(S setter,List<HystrixProperty> properties,Map<String,PropSetter<S,String>> propMap,String type){
  if (properties != null && properties.size() > 0) {
    for (    HystrixProperty property : properties) {
      validate(property);
      if (!propMap.containsKey(property.name())) {
        throw new IllegalArgumentException("unknown " + type + " property: " + property.name());
      }
      propMap.get(property.name()).set(setter,property.value());
    }
  }
  return setter;
}
