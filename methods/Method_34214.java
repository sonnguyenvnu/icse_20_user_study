@Override public boolean addApprovals(final Collection<Approval> approvals){
  if (logger.isDebugEnabled()) {
    logger.debug(String.format("adding approvals: [%s]",approvals));
  }
  boolean success=true;
  for (  Approval approval : approvals) {
    if (!updateApproval(refreshApprovalStatement,approval)) {
      if (!updateApproval(addApprovalStatement,approval)) {
        success=false;
      }
    }
  }
  return success;
}
