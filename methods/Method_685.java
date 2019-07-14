public boolean applyName(JSONSerializer jsonBeanDeser,Object object,String key){
  if (jsonBeanDeser.propertyPreFilters != null) {
    for (    PropertyPreFilter filter : jsonBeanDeser.propertyPreFilters) {
      if (!filter.apply(jsonBeanDeser,object,key)) {
        return false;
      }
    }
  }
  if (this.propertyPreFilters != null) {
    for (    PropertyPreFilter filter : this.propertyPreFilters) {
      if (!filter.apply(jsonBeanDeser,object,key)) {
        return false;
      }
    }
  }
  return true;
}
