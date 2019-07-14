public boolean isClose(Node c){
  if (c instanceof Delimeter) {
    return delimMap.values().contains(((Delimeter)c).shape);
  }
 else {
    return false;
  }
}
