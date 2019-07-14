public List<BeforeFilter> getBeforeFilters(){
  if (beforeFilters == null) {
    beforeFilters=new ArrayList<BeforeFilter>();
    writeDirect=false;
  }
  return beforeFilters;
}
