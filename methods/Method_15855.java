@Override public boolean upload(String fileName,InputStream input){
  return doExecute(Unchecked.function(client -> client.storeFile(fileName,input)));
}
