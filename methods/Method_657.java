public boolean hasPropertyFilters(SerializeFilterable filterable){
  return (propertyFilters != null && propertyFilters.size() > 0) || (filterable.propertyFilters != null && filterable.propertyFilters.size() > 0);
}
