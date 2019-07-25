private void queue(Iterable<SInterfaceConcept> superInterfaces){
  if (superInterfaces != null) {
    for (    SInterfaceConcept ic : superInterfaces) {
      myInterfaces.add(ic);
    }
  }
}
