protected String generateAlias(Instance instance){
  return instance.getRegistration().getName() + "_" + instance.getId();
}
