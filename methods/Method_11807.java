private void addArrayValues(ParameterSignature sig,String name,List<PotentialAssignment> list,Object array){
  for (int i=0; i < Array.getLength(array); i++) {
    Object value=Array.get(array,i);
    if (sig.canAcceptValue(value)) {
      list.add(PotentialAssignment.forValue(name + "[" + i + "]",value));
    }
  }
}
