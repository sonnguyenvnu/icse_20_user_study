private Optional<Nullness> getNullness(InferenceVariable iv){
  Optional<Nullness> result;
  if (iv instanceof ProperInferenceVar) {
    return Optional.of(((ProperInferenceVar)iv).nullness());
  }
 else   if ((result=inferredMemoTable.get(iv)) != null) {
    return result;
  }
 else {
    inferredMemoTable.put(iv,Optional.empty());
    result=constraintGraph.predecessors(iv).stream().map(this::getNullness).filter(Optional::isPresent).map(Optional::get).reduce(Nullness::leastUpperBound);
    if (!result.isPresent()) {
      result=constraintGraph.successors(iv).stream().map(this::getNullness).filter(Optional::isPresent).map(Optional::get).reduce(Nullness::greatestLowerBound);
    }
    checkState(!inferredMemoTable.put(iv,result).isPresent());
    return result;
  }
}
