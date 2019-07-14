static Changes create(ImmutableList<Double> originalCost,ImmutableList<Double> assignmentCost,ImmutableList<ParameterPair> changedPairs){
  return new AutoValue_Changes(originalCost,assignmentCost,changedPairs);
}
