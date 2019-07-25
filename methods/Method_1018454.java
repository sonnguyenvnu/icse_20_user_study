@Override public void handle(JobClient client,ActivatedJob job){
  System.out.println("fetch goods");
  client.newCompleteCommand(job.getKey()).send().join();
}
