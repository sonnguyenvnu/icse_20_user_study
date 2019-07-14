@Override public boolean revokeApprovals(Collection<Approval> approvals){
  boolean success=true;
  for (  Approval approval : approvals) {
    Collection<Approval> collection=getApprovals(approval);
    boolean removed=collection.remove(approval);
    if (!removed) {
      success=false;
    }
  }
  return success;
}
