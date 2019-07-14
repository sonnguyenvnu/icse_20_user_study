private void addCommonParameters(MethodSpec.Builder constructor){
  constructor.addParameter(valueSpec);
  constructor.addParameter(valueRefQueueSpec);
  constructor.addParameter(int.class,"weight");
  constructor.addParameter(long.class,"now");
}
