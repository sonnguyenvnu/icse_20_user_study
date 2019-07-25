public int anycasts(boolean sync){
  return sync ? sync_anycasts.get() : async_anycasts.get();
}
