private Set<NameDeclaration> searchUpward(PLSQLNameOccurrence nameOccurrence,Scope scope){
  if (LOGGER.isLoggable(Level.FINEST)) {
    LOGGER.finest("checking scope " + scope + " for name occurrence " + nameOccurrence);
  }
  if (!scope.contains(nameOccurrence) && scope.getParent() != null) {
    if (LOGGER.isLoggable(Level.FINEST)) {
      LOGGER.finest("moving up fm " + scope + " to " + scope.getParent());
    }
    return searchUpward(nameOccurrence,scope.getParent());
  }
  if (scope.contains(nameOccurrence)) {
    if (LOGGER.isLoggable(Level.FINEST)) {
      LOGGER.finest("found it!");
    }
    return scope.addNameOccurrence(nameOccurrence);
  }
  return new HashSet<>();
}
