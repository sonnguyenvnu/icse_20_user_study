@Override public SNode put(SNodeId key,SNode value){
  if (key instanceof StringBasedIdForJavaStubMethods) {
    StringBasedIdForJavaStubMethods stubId=(StringBasedIdForJavaStubMethods)key;
    myFallbackForJavaStubMethodRefsMap.put(stubId.getIdWithReturnTypeNoPrefix(),value);
    return myStringBasedIds.put(stubId.getIdWithoutReturnTypeNoPrefix(),value);
  }
 else   if (key instanceof StringBasedId) {
    return myStringBasedIds.put(((StringBasedId)key).getId(),value);
  }
  return myOtherMap.put(key,value);
}
