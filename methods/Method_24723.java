static private boolean[] getTweakedTabs(List<List<Handle>> handles){
  boolean[] outgoing=new boolean[handles.size()];
  for (int i=0; i < handles.size(); i++) {
    for (    Handle h : handles.get(i)) {
      if (h.valueChanged()) {
        outgoing[i]=true;
      }
    }
  }
  return outgoing;
}
