public String discard(){
  client.getMany(getPipelinedResponseLength());
  client.discard();
  inTransaction=false;
  clean();
  return client.getStatusCodeReply();
}
