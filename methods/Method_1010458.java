public NodeMatcher association(@NotNull SReferenceLink l,@NotNull String linkVarName){
  if (myReferenceToVariableName == null) {
    myReferenceToVariableName=new HashMap<>(8);
  }
  myReferenceToVariableName.put(l,linkVarName);
  return this;
}
