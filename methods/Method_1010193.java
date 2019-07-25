@Override public String describe(){
  return String.format("Template declaration %s from %s",myTemplateName == null ? myNodeReference.getNodeId() : myTemplateName,getSourceModel().getName());
}
