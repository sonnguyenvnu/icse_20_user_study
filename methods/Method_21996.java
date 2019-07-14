boolean addJobExecutionEvent(final JobExecutionEvent jobExecutionEvent){
  if (null == jobExecutionEvent.getCompleteTime()) {
    return insertJobExecutionEvent(jobExecutionEvent);
  }
 else {
    if (jobExecutionEvent.isSuccess()) {
      return updateJobExecutionEventWhenSuccess(jobExecutionEvent);
    }
 else {
      return updateJobExecutionEventFailure(jobExecutionEvent);
    }
  }
}
