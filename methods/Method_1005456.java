@Override public void build(){
  if (!StringUtils.isBlank(name) && !StringUtils.isBlank(clazz)) {
    elEngine.setVariable(name,clazz);
  }
  super.build();
}
