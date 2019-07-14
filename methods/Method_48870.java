@Override public boolean hasNegation(){
  return !this.stream().anyMatch(internalCondition -> !(internalCondition.hasNegation()));
}
