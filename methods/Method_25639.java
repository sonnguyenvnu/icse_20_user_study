private static Matcher<IdentifierTree> identifierHasName(final String name){
  return (item,state) -> item.getName().contentEquals(name);
}
