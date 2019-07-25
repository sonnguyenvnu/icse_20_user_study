@Override public SAbstractConcept next(){
  SAbstractConcept rv=myQueue.removeFirst();
  final Iterable<SInterfaceConcept> superInterfaces;
  final SConcept parentConcept;
  if (rv instanceof SInterfaceConcept) {
    superInterfaces=((SInterfaceConcept)rv).getSuperInterfaces();
    parentConcept=null;
  }
 else {
    assert rv instanceof SConcept;
    parentConcept=((SConcept)rv).getSuperConcept();
    superInterfaces=((SConcept)rv).getSuperInterfaces();
  }
  for (  SAbstractConcept i : superInterfaces) {
    myQueue.addLast(i);
  }
  if (parentConcept != null) {
    myQueue.addLast(parentConcept);
  }
  return rv;
}
