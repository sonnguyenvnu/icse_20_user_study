protected boolean applyLabel(JSONSerializer jsonBeanDeser,String label){
  if (jsonBeanDeser.labelFilters != null) {
    for (    LabelFilter propertyFilter : jsonBeanDeser.labelFilters) {
      if (!propertyFilter.apply(label)) {
        return false;
      }
    }
  }
  if (this.labelFilters != null) {
    for (    LabelFilter propertyFilter : this.labelFilters) {
      if (!propertyFilter.apply(label)) {
        return false;
      }
    }
  }
  return true;
}
