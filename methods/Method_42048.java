public boolean clearSelected(){
  boolean changed=true;
  for (int i=0; i < albums.size(); i++) {
    boolean b=albums.get(i).setSelected(false);
    if (b)     notifyItemChanged(i);
    changed&=b;
  }
  selectedCount=0;
  stopSelection();
  return changed;
}
