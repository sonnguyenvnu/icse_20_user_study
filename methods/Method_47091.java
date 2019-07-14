private SSHClient create(@NonNull String host,int port,@NonNull String hostKey,@NonNull String username,@Nullable String password,@Nullable KeyPair keyPair){
  try {
    AsyncTaskResult<SSHClient> taskResult=new SshAuthenticationTask(host,port,hostKey,username,password,keyPair).execute().get();
    SSHClient client=taskResult.result;
    return client;
  }
 catch (  InterruptedException e) {
    throw new RuntimeException(e);
  }
catch (  ExecutionException e) {
    throw new RuntimeException(e);
  }
}
