/** 
 * Given a set of inference variables to resolve, let V be the union of this set and all variables upon which the resolution of at least one variable in this set depends. <p> ... <p> Otherwise, let { ?1, ..., ?n } be a non-empty subset of uninstantiated variables in V such that i) for all i (1 ? i ? n), if ?i depends on the resolution of a variable ?, then either ? has an instantiation or there is some j such that ? = ?j; and Resolution proceeds by generating an instantiation for each of ?1, ..., ?n based on the bounds in the bound set:
 * @return true, if 'variables' is a resolvable subset
 */
public static boolean isProperSubsetOfVariables(List<Variable> variables,Map<Variable,JavaTypeDefinition> instantiations,Map<Variable,Set<Variable>> dependencies,List<Bound> bounds){
  for (  Variable unresolvedVariable : variables) {
    for (    Variable dependency : dependencies.get(unresolvedVariable)) {
      if (!instantiations.containsKey(dependency) && !Objects.equals(unresolvedVariable,dependency) && !boundsHaveAnEqualityBetween(variables,dependency,bounds)) {
        return false;
      }
    }
  }
  return true;
}
