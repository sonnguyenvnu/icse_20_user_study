private void addSinglePointFields(ParameterSignature sig,List<PotentialAssignment> list){
  for (  final Field field : getSingleDataPointFields(sig)) {
    Object value=getStaticFieldValue(field);
    if (sig.canAcceptValue(value)) {
      list.add(PotentialAssignment.forValue(field.getName(),value));
    }
  }
}
