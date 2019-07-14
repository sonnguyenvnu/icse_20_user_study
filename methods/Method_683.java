public List<ContextValueFilter> getContextValueFilters(){
  if (contextValueFilters == null) {
    contextValueFilters=new ArrayList<ContextValueFilter>();
    writeDirect=false;
  }
  return contextValueFilters;
}
