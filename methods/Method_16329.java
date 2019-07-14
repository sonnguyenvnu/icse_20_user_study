@Override public boolean support(String type){
  return "dict".equals(type) && dictDefineRepository != null;
}
