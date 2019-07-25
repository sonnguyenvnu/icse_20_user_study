private void reset(){
  myInterfaces.clear();
  if (myStart instanceof SInterfaceConcept) {
    myCurrent=null;
    myInterfaces.add((SInterfaceConcept)myStart);
  }
 else {
    myCurrent=(SConcept)myStart;
  }
}
