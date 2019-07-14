public List<ValueFilter> getValueFilters(){
  if (valueFilters == null) {
    valueFilters=new ArrayList<ValueFilter>();
    writeDirect=false;
  }
  return valueFilters;
}
