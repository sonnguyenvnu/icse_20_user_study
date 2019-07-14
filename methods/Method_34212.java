@Override public Collection<Approval> getApprovals(String userId,String clientId){
  Approval approval=new Approval();
  approval.setUserId(userId);
  approval.setClientId(clientId);
  return Collections.unmodifiableCollection(getApprovals(approval));
}
