@Override public void handle(JobClient client,ActivatedJob job){
  try {
    String traceId=(String)job.getVariablesAsMap().get("traceId");
    String refId=(String)job.getVariablesAsMap().get("refId");
    long amount=(Integer)job.getVariablesAsMap().get("amount");
    System.out.println("retrieved payment " + amount + " for " + refId);
  }
 catch (  Exception e) {
    throw new RuntimeException("Could not parse payload: " + e.getMessage(),e);
  }
  client.newCompleteCommand(job.getKey()).send().join();
}
