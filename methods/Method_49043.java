public static void verifyArgsMustBeEitherIdOrElement(Object... ids){
  assert ids.length > 0;
  int numElements=0;
  for (  Object id : ids) {
    if (id instanceof Element)     numElements++;
  }
  if (numElements > 0 && numElements < ids.length)   throw Graph.Exceptions.idArgsMustBeEitherIdOrElement();
}
