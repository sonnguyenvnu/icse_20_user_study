public SNode resolve(String moduleName,String moduleId){
  SNode result=null;
  if (moduleId != null) {
    result=myId2Module.get(moduleId);
  }
  if (result == null && moduleName != null) {
    result=myName2Module.get(moduleName);
  }
  return result;
}
