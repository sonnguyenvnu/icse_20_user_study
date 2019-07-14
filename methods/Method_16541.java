@Override public boolean isSupport(String type,String action,String config){
  return "SCOPE_BY_USER".equalsIgnoreCase(type);
}
