public List<PotentialAssignment> potentialsForNextUnassigned() throws Throwable {
  ParameterSignature unassigned=nextUnassigned();
  List<PotentialAssignment> assignments=getSupplier(unassigned).getValueSources(unassigned);
  if (assignments.isEmpty()) {
    assignments=generateAssignmentsFromTypeAlone(unassigned);
  }
  return assignments;
}
