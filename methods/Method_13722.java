public boolean isOutOfService(){
  return !started.get() && startException == null;
}
