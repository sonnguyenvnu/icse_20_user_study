@Deprecated public static boolean match(SNode left,SNode right,Equations equations,@Nullable EquationInfo info){
  THashSet<Pair<SNode,SNode>> matchingPairs=new THashSet<>();
  boolean match=match(left,right,matchingPairs);
  if (match && equations != null) {
    equations.addEquations(matchingPairs,info);
  }
  return match;
}
