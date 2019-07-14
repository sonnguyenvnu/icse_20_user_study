public int getParameterCount(){
  return getFirstChildOfType(ASTFormalParameters.class).getParameterCount();
}
