public void execute(Scope startingScope){
  Set<NameDeclaration> found=searchUpward(occ,startingScope);
  if (LOGGER.isLoggable(Level.FINEST)) {
    LOGGER.finest("found " + found);
  }
  declarations.addAll(found);
}
