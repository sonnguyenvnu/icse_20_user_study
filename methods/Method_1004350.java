private void post(MessageQueryIndex index){
  if (RetrySubjectUtils.isDeadRetrySubject(index.getSubject())) {
    saveDeadMessage(index);
    return;
  }
  indexBatchBackup.add(index,consumer);
}
