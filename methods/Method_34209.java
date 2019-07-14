@Override public boolean addApprovals(Collection<Approval> approvals){
  for (  Approval approval : approvals) {
    Collection<Approval> collection=getApprovals(approval);
    collection.add(approval);
  }
  return true;
}
