protected Object[] createMatches(){
  Object[] a=new Object[_config.selection.size()];
  for (int i=0; i < a.length; i++) {
    a[i]=_config.selection.get(i).value;
  }
  return a;
}
