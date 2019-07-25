@Override public <T extends Packet>boolean contains(Class<T> clazz){
  return get(clazz) != null;
}
