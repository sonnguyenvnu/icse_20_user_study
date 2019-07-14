@Override public void execute(CurrentPath path){
  if (maxNumberOfViolationsReached()) {
    return;
  }
  Map<String,Usage> usagesByVarName=new HashMap<>();
  for (  DataFlowNode inode : path) {
    if (inode.getVariableAccess() != null) {
      for (      VariableAccess va : inode.getVariableAccess()) {
        Usage lastUsage=usagesByVarName.get(va.getVariableName());
        if (lastUsage != null) {
          checkVariableAccess(inode,va,lastUsage);
        }
        Usage newUsage=new Usage(va.getAccessType(),inode);
        usagesByVarName.put(va.getVariableName(),newUsage);
      }
    }
  }
}
