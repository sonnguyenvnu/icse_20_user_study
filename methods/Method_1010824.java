@Override public void add(Style child){
  if (myChildren == null) {
    myChildren=new LinkedList<>();
  }
  myChildren.add(child);
  child.setParent(this,getNonDefaultValuedAttributes());
}
