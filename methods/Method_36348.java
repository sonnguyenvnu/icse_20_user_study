@Override public String getSpringParent(){
  List<String> name=getFormattedModuleInfo(SofaModuleFrameworkConstants.SPRING_PARENT);
  return name == null ? null : name.get(0);
}
