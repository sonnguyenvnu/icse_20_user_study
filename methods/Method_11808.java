private void addIterableValues(ParameterSignature sig,String name,List<PotentialAssignment> list,Iterable<?> iterable){
  Iterator<?> iterator=iterable.iterator();
  int i=0;
  while (iterator.hasNext()) {
    Object value=iterator.next();
    if (sig.canAcceptValue(value)) {
      list.add(PotentialAssignment.forValue(name + "[" + i + "]",value));
    }
    i+=1;
  }
}
