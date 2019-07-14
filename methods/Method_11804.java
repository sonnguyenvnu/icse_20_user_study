private void addMultiPointFields(ParameterSignature sig,List<PotentialAssignment> list){
  for (  final Field field : getDataPointsFields(sig)) {
    Class<?> type=field.getType();
    addDataPointsValues(type,sig,field.getName(),list,getStaticFieldValue(field));
  }
}
