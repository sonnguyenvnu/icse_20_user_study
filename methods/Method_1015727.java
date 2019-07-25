public int multicasts(boolean sync){
  return sync ? sync_multicasts.get() : async_multicasts.get();
}
