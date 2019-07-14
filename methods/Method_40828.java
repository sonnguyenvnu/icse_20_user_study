public boolean isOpen(Node c){
  if (c instanceof Delimeter) {
    return delimMap.keySet().contains(((Delimeter)c).shape);
  }
 else {
    return false;
  }
}
