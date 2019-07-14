@Override public void execute(CurrentPath path){
  Map<String,Usage> hash=new HashMap<>();
  for (Iterator<DataFlowNode> i=path.iterator(); i.hasNext(); ) {
    DataFlowNode inode=i.next();
    if (inode.getVariableAccess() == null) {
      continue;
    }
    for (int j=0; j < inode.getVariableAccess().size(); j++) {
      VariableAccess va=inode.getVariableAccess().get(j);
      Usage u=hash.get(va.getVariableName());
      if (u != null) {
        if (va.isDefinition() && va.accessTypeMatches(u.accessType)) {
          addViolation(rc,u.node.getNode(),va.getVariableName());
        }
      }
      u=new Usage(va.getAccessType(),inode);
      hash.put(va.getVariableName(),u);
    }
  }
}
