Changes computeAssignments(){
  int[] assignments=new HungarianAlgorithm(costMatrix).execute();
  ImmutableList<Parameter> formalsWithChange=formals.stream().filter(f -> assignments[f.index()] != f.index()).collect(toImmutableList());
  if (formalsWithChange.isEmpty()) {
    return Changes.empty();
  }
  ImmutableList<Double> originalCost=formalsWithChange.stream().map(f2 -> costMatrix[f2.index()][f2.index()]).collect(toImmutableList());
  ImmutableList<Double> assignmentCost=formalsWithChange.stream().map(f1 -> costMatrix[f1.index()][assignments[f1.index()]]).collect(toImmutableList());
  ImmutableList<ParameterPair> changes=formalsWithChange.stream().map(f -> ParameterPair.create(f,actuals.get(assignments[f.index()]))).collect(toImmutableList());
  return Changes.create(originalCost,assignmentCost,changes);
}
