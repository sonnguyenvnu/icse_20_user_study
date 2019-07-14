@Override public boolean supports(KeyInformation information,JanusGraphPredicate janusgraphPredicate){
  return janusgraphPredicate == Cmp.EQUAL || janusgraphPredicate == Contain.IN;
}
