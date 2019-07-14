static int findInitialComponentPosition(List<ComponentTreeHolder> holders,boolean traverseBackwards){
  if (traverseBackwards) {
    for (int i=holders.size() - 1; i >= 0; i--) {
      if (holders.get(i).getRenderInfo().rendersComponent()) {
        return i;
      }
    }
  }
 else {
    for (int i=0, size=holders.size(); i < size; i++) {
      if (holders.get(i).getRenderInfo().rendersComponent()) {
        return i;
      }
    }
  }
  return -1;
}
