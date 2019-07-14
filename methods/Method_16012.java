private static ModuleInfo parse(Class type){
  String classpath=getClassPath(type);
  return nameModuleInfoRepository.values().stream().filter(moduleInfo -> classpath.equals(moduleInfo.classPath)).findFirst().orElse(noneInfo);
}
