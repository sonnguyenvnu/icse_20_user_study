@Override public boolean isQNF(){
  return !this.stream().anyMatch(internalCondition -> internalCondition instanceof AndJanusPredicate || !internalCondition.isQNF());
}
