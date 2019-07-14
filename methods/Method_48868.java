@Override public boolean isQNF(){
  for (  final JanusGraphPredicate internalCondition : this) {
    if (!internalCondition.isQNF()) {
      return false;
    }
  }
  return true;
}
