@Override public List<PotentialAssignment> getValueSources(ParameterSignature sig){
  Object[] enumValues=enumType.getEnumConstants();
  List<PotentialAssignment> assignments=new ArrayList<PotentialAssignment>();
  for (  Object value : enumValues) {
    assignments.add(PotentialAssignment.forValue(value.toString(),value));
  }
  return assignments;
}
