private boolean validate(@NonNull SSHClient client){
  return client.isConnected() && client.isAuthenticated();
}
