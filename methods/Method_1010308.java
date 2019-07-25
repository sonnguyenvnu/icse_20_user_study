@Override @NotNull public MappingConfig_SimpleRef copy(){
  MappingConfig_SimpleRef result=new MappingConfig_SimpleRef();
  result.myModelUID=myModelUID;
  result.myNodeID=myNodeID;
  result.myConfigName=myConfigName;
  return result;
}
