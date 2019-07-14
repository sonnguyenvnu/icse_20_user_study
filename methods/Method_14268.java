@Override public boolean fieldAlsoHasFields(String name){
  int c=size();
  return (c > 0 && get(0).fieldAlsoHasFields(name));
}
