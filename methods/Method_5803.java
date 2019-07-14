@Override public void clearRequestProperty(String name){
  Assertions.checkNotNull(name);
  requestProperties.remove(name);
}
