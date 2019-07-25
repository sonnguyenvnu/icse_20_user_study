@Override public SAbstractConcept next(){
  if (myCurrent == null) {
    final SInterfaceConcept rv=myInterfaces.removeFirst();
    queue(rv.getSuperInterfaces());
    return rv;
  }
 else {
    SConcept rv=myCurrent;
    queue(myCurrent.getSuperInterfaces());
    myCurrent=myCurrent.getSuperConcept();
    return rv;
  }
}
