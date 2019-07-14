/** 
 * @return A list of variables which have no direct instantiations
 */
public static Set<Variable> getUninstantiatedVariables(List<Bound> bounds){
  Set<Variable> result=getMentionedVariables(bounds);
  result.removeAll(getInstantiations(bounds).keySet());
  return result;
}
