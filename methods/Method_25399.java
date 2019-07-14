/** 
 * Matches an AST node that represents a method declaration, based on the list of variableMatchers. Applies the variableMatcher at index n to the parameter at index n and returns true iff they all match. Returns false if the number of variableMatchers provided does not match the number of parameters. <p>If you pass no variableMatchers, this will match methods with no parameters.
 * @param variableMatcher a list of matchers to apply to the parameters of the method
 */
public static Matcher<MethodTree> methodHasParameters(final List<Matcher<VariableTree>> variableMatcher){
  return (methodTree,state) -> {
    if (methodTree.getParameters().size() != variableMatcher.size()) {
      return false;
    }
    int paramIndex=0;
    for (    Matcher<VariableTree> eachVariableMatcher : variableMatcher) {
      if (!eachVariableMatcher.matches(methodTree.getParameters().get(paramIndex++),state)) {
        return false;
      }
    }
    return true;
  }
;
}
