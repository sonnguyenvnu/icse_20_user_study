public void reset(List<BeanElement> beanElementList){
  this.beanElementList=beanElementList;
  this.propertyMapperMap.clear();
  this.mapperPropertyMap.clear();
  this.elementMap.clear();
  for (  BeanElement e : this.beanElementList) {
    String property=e.getProperty();
    String mapper=e.getMapper();
    this.elementMap.put(property,e);
    this.propertyMapperMap.put(property,mapper);
    this.mapperPropertyMap.put(mapper,property);
  }
}
