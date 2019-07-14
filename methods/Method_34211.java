private Collection<Approval> getApprovals(Approval approval){
  Key key=new Key(approval.getUserId(),approval.getClientId());
  if (!map.containsKey(key)) {
    map.putIfAbsent(key,new HashSet<Approval>());
  }
  return map.get(key);
}
