protected String processKey(JSONSerializer jsonBeanDeser,Object object,String key,Object propertyValue){
  if (jsonBeanDeser.nameFilters != null) {
    for (    NameFilter nameFilter : jsonBeanDeser.nameFilters) {
      key=nameFilter.process(object,key,propertyValue);
    }
  }
  if (this.nameFilters != null) {
    for (    NameFilter nameFilter : this.nameFilters) {
      key=nameFilter.process(object,key,propertyValue);
    }
  }
  return key;
}
