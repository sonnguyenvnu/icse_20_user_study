@Override public List<String> getFilterClassNames(){
  List<String> names=new ArrayList<String>();
  for (  Filter filter : filters) {
    names.add(filter.getClass().getName());
  }
  return names;
}
