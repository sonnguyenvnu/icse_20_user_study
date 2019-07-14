public static List<JavaTypeDefinition> getLowerBoundsOf(Variable var,List<Bound> bounds){
  List<JavaTypeDefinition> result=new ArrayList<>();
  for (  Bound bound : bounds) {
    if (bound.ruleType() == SUBTYPE && bound.rightVariable() == var) {
      if (bound.isLeftVariable()) {
        throw new ResolutionFailedException();
      }
      result.add(bound.leftProper());
    }
  }
  return result;
}
