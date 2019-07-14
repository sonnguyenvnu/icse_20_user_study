@Override public String getJobFailureString(Job j){
  try {
    JobStatus js=j.getStatus();
    return String.format("state=%s, failureinfo=%s",js.getState(),js.getFailureInfo());
  }
 catch (  IOException|InterruptedException e) {
    throw new JanusGraphException(e);
  }
}
