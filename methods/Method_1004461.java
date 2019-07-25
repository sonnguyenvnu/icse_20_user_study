void heartbeat(){
  renew();
  retryTask.cancel();
  offlineTask.cancel();
}
