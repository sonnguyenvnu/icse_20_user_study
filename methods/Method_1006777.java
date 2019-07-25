public void put(String property,String mapper){
  this.propertyMapperMap.put(property,mapper);
  this.mapperPropertyMap.put(mapper,property);
}
