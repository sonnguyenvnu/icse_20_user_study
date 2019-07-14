@Override public boolean isAvailable(){
  return !discoveryClient.getServices().isEmpty();
}
