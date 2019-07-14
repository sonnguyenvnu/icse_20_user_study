@Override public List<PotentialAssignment> getValueSources(ParameterSignature sig) throws Throwable {
  List<PotentialAssignment> list=new ArrayList<PotentialAssignment>();
  addSinglePointFields(sig,list);
  addMultiPointFields(sig,list);
  addSinglePointMethods(sig,list);
  addMultiPointMethods(sig,list);
  return list;
}
