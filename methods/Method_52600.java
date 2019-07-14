public static List<JavaTypeDefinition> inferTypes(List<Constraint> constraints,List<Bound> bounds,List<Variable> variables){
  List<Bound> newBounds=new ArrayList<>();
  while (!constraints.isEmpty()) {
    List<BoundOrConstraint> reduceResult=constraints.remove(constraints.size() - 1).reduce();
    if (reduceResult == null) {
      return null;
    }
    for (    BoundOrConstraint boundOrConstraint : reduceResult) {
      if (boundOrConstraint instanceof Bound) {
        newBounds.add((Bound)boundOrConstraint);
      }
 else       if (boundOrConstraint instanceof Constraint) {
        constraints.add((Constraint)boundOrConstraint);
      }
 else {
        throw new IllegalStateException("Unknown BoundOrConstraint subclass");
      }
    }
    constraints.addAll(incorporateBounds(bounds,newBounds));
    newBounds.clear();
  }
  Map<Variable,JavaTypeDefinition> resolutionResult=resolveVariables(bounds);
  List<JavaTypeDefinition> result=new ArrayList<>();
  for (  Variable variable : variables) {
    result.add(resolutionResult.get(variable));
  }
  return result;
}
