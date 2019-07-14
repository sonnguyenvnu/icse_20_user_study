@Override public List<PotentialAssignment> getValueSources(ParameterSignature sig){
  return Arrays.asList(PotentialAssignment.forValue("true",true),PotentialAssignment.forValue("false",false));
}
