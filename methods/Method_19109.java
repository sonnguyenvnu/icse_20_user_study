public static Map<Class<? extends Annotation>,DelegateMethodDescription> getDiffSectionSpecDelegatesMap(DiffSectionSpecModel specModel){
  Map<Class<? extends Annotation>,DelegateMethodDescription> diffSectionSpecDelegateMethodsMap=getTreeMap();
  diffSectionSpecDelegateMethodsMap.putAll(DIFF_SECTION_SPEC_DELEGATE_METHODS_MAP);
  addServiceAwareDelegateMethodDescriptions(diffSectionSpecDelegateMethodsMap,specModel);
  return Collections.unmodifiableMap(diffSectionSpecDelegateMethodsMap);
}
