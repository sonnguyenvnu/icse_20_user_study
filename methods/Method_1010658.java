public static boolean match(SNode left,SNode right,Collection<Pair<SNode,SNode>> matchingPairs){
  if (left == right)   return true;
  if (left == null || right == null)   return false;
  if (TypesUtil.isVariable(left) || TypesUtil.isVariable(right)) {
    if (matchingPairs != null) {
      matchingPairs.add(new Pair<>(left,right));
    }
    return true;
  }
  MatchingNodesCollector matchingNodesCollector=new MatchingNodesCollector();
  boolean match=MatchingUtil.matchNodes(left,right,matchingNodesCollector,false);
  if (match && matchingPairs != null) {
    matchingPairs.addAll(matchingNodesCollector.myMatchingPairs);
  }
  return match;
}
