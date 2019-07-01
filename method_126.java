private String _XXXXX_(TaskAttemptExecutionAPIEntity taskAttemptInfo){
  return taskAttemptInfo.getTags().get(MRJobTagName.TASK_TYPE.toString());
}