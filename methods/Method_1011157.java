@Override public IInterfacedFinder instantiate(int token) throws IllegalArgumentException {
switch (token) {
case 0:
    return new IdentityFinder_Finder();
case 1:
  return new NodeAndDescendantsUsages_Finder();
default :
throw new IllegalArgumentException(String.format("Illegal identifier of a finder implementation: %d",token));
}
}
