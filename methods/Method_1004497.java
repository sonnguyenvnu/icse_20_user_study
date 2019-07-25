public boolean destroy(String subject,String group,String consumerId){
  PullLog pullLog=get(subject,group,consumerId);
  if (pullLog == null)   return false;
  pullLog.destroy();
  remove(subject,group,consumerId);
  return true;
}
