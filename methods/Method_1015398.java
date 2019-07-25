public boolean exists(){
  return cache.get(getPath()) != null;
}
