public List<NameFilter> getNameFilters(){
  if (nameFilters == null) {
    nameFilters=new ArrayList<NameFilter>();
    writeDirect=false;
  }
  return nameFilters;
}
