public Pipeline pipelined(){
  pipeline=new Pipeline();
  pipeline.setClient(client);
  return pipeline;
}
