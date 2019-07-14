/** 
 * Resolve unresolved variables in a list of bounds.
 */
public static Map<Variable,JavaTypeDefinition> resolveVariables(List<Bound> bounds){
  Map<Variable,Set<Variable>> variableDependencies=getVariableDependencies(bounds);
  Map<Variable,JavaTypeDefinition> instantiations=getInstantiations(bounds);
  List<Variable> uninstantiatedVariables=new ArrayList<>(getUninstantiatedVariables(bounds));
  while (!uninstantiatedVariables.isEmpty()) {
    List<Variable> variablesToResolve=null;
    for (    List<Variable> variableSet : new Combinations(uninstantiatedVariables)) {
      if (isProperSubsetOfVariables(variableSet,instantiations,variableDependencies,bounds)) {
        variablesToResolve=variableSet;
        break;
      }
    }
    if (variablesToResolve == null) {
      throw new ResolutionFailedException();
    }
    for (    Variable var : variablesToResolve) {
      List<JavaTypeDefinition> lowerBounds=getLowerBoundsOf(var,bounds);
      instantiations.put(var,lub(lowerBounds));
    }
    uninstantiatedVariables.removeAll(variablesToResolve);
  }
  return instantiations;
}
