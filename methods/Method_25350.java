/** 
 * Returns the provided set of operators if they are all compound-assignment operators. Otherwise, throws an IllegalArgumentException.
 */
private static Set<Kind> validateOperators(Set<Kind> kinds){
  for (  Kind kind : kinds) {
    if (!COMPOUND_ASSIGNMENT_OPERATORS.contains(kind)) {
      throw new IllegalArgumentException(kind.name() + " is not a compound-assignment operator.");
    }
  }
  return kinds;
}
