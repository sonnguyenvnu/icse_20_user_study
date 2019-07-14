@Override @SuppressWarnings("unchecked") public <T extends Serializable>T removeAttributes(String name){
  return (T)attributes.remove(name);
}
