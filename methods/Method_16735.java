@Override public void setCandidate(String doingUserId,Task task){
  if (task == null) {
    return;
  }
  if (task.getTaskDefinitionKey() != null) {
    List<CandidateInfo> candidateInfoList=processConfigurationService.getActivityConfiguration(doingUserId,task.getProcessDefinitionId(),task.getTaskDefinitionKey()).getCandidateInfo(task);
    if (CollectionUtils.isEmpty(candidateInfoList)) {
      logger.warn("??:{}???????,?????????!",task);
    }
 else {
      for (      CandidateInfo candidateInfo : candidateInfoList) {
        Authentication user=candidateInfo.user();
        if (user != null) {
          taskService.addCandidateUser(task.getId(),user.getUser().getId());
        }
      }
    }
  }
 else {
    logger.warn("???????????,task:{}",task);
  }
}
