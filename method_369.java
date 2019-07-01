@Override public List<AlertPublishEvent> _XXXXX_(String policyId,int size){
  if (size <= 0) {
    LOG.info("Invalid parameter size <= 0");
    return new ArrayList<>();
  }
  return handler.getAlertEventByPolicyId(policyId,size);
}