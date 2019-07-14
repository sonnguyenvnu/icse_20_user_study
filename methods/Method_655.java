public boolean checkValue(SerializeFilterable filterable){
  return (valueFilters != null && valueFilters.size() > 0) || (contextValueFilters != null && contextValueFilters.size() > 0) || (filterable.valueFilters != null && filterable.valueFilters.size() > 0) || (filterable.contextValueFilters != null && filterable.contextValueFilters.size() > 0) || out.writeNonStringValueAsString;
}
