@Override public boolean support(String type){
  return type != null && (getTypeString().equalsIgnoreCase(type));
}
