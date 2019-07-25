@Override public void handle(JobClient client,ActivatedJob job){
  System.out.println("ship goods");
  client.newCompleteCommand(job.getKey()).send().join();
}
