@Override public void success(List<ScheduleSetRecord> indexList,Set<String> messageIds){
  retry(indexList,messageIds);
}
