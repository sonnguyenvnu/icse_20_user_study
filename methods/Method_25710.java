/** 
 * Return the first regular expression from the list of overloaded words which matches the parameter name.
 */
protected String findMatch(Parameter parameter){
  for (  String regex : overloadedNamesRegexs) {
    if (parameter.name().matches(regex)) {
      return regex;
    }
  }
  return null;
}
