private void addDataPointsValues(Class<?> type,ParameterSignature sig,String name,List<PotentialAssignment> list,Object value){
  if (type.isArray()) {
    addArrayValues(sig,name,list,value);
  }
 else   if (Iterable.class.isAssignableFrom(type)) {
    addIterableValues(sig,name,list,(Iterable<?>)value);
  }
}
