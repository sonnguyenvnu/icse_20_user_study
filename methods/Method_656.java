public boolean hasNameFilters(SerializeFilterable filterable){
  return (nameFilters != null && nameFilters.size() > 0) || (filterable.nameFilters != null && filterable.nameFilters.size() > 0);
}
