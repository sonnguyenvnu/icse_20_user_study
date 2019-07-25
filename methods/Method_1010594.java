@Override public SNode remove(SNodeId key){
  if (key instanceof StringBasedIdForJavaStubMethods) {
    StringBasedIdForJavaStubMethods stubId=(StringBasedIdForJavaStubMethods)key;
    myFallbackForJavaStubMethodRefsMap.remove(stubId.getIdWithReturnTypeNoPrefix());
    return myStringBasedIds.remove(stubId.getIdWithoutReturnTypeNoPrefix());
  }
 else   if (key instanceof StringBasedId) {
    return myStringBasedIds.remove(((StringBasedId)key).getId());
  }
  return myOtherMap.remove(key);
}
