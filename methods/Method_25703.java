/** 
 * Return true if the replacement name is equal to the argument name for any replacement position.
 */
private static boolean anyArgumentsMatch(List<ParameterPair> changedPairs,List<Parameter> arguments){
  return changedPairs.stream().anyMatch(change -> Objects.equals(change.actual().text(),arguments.get(change.formal().index()).text()));
}
