@Deprecated public NodeMatcher property(@NotNull SProperty p,@NotNull String patternVarName){
  if (myPropertyToVariableName_old == null) {
    myPropertyToVariableName_old=new HashMap<>(8);
  }
  myPropertyToVariableName_old.put(p,patternVarName);
  return this;
}
