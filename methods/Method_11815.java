private List<PotentialAssignment> generateAssignmentsFromTypeAlone(ParameterSignature unassigned){
  Class<?> paramType=unassigned.getType();
  if (paramType.isEnum()) {
    return new EnumSupplier(paramType).getValueSources(unassigned);
  }
 else   if (paramType.equals(Boolean.class) || paramType.equals(boolean.class)) {
    return new BooleanSupplier().getValueSources(unassigned);
  }
 else {
    return emptyList();
  }
}
