public static ModuleInfo getModuleByClass(Class type){
  return classModuleInfoRepository.computeIfAbsent(type,ModuleUtils::parse);
}
